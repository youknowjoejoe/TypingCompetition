package test;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * June 2017
 * 
 */

import progressDisplayComponents.ColorfulProgressDCPanel;
import progressDisplayComponents.GraphProgressDCPanel;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * May 2017
 * 
 */

import progressDisplayComponents.ProgressDC;
import typingLogics.LoopingTest;
import typingLogics.StandardTypingLogic;
import util.IntervalUpdater;
import wordDisplayComponents.WordDisplayComponent;
import wordDisplayComponents.WordDisplayComponent1;
import wordDisplayComponents.WordDisplayComponent2;

import core.Builder;
import core.DisplayComponent;
import core.Menu;
import core.OptionActionListener;
import core.TypingLogic;

public class Main {
	
	public static void main(String[] args){
		
		Builder<TypingLogic> stlDefault = new Builder<TypingLogic>(
				"Default Typing Test",
				"50 most common word, 1 minute")
		{
			@Override
			public TypingLogic build() {
				return new StandardTypingLogic(LoopingTest.standard);
			}
		};
		Builder<TypingLogic> stlRickAstley = new Builder<TypingLogic>(
				"Never Gonna Type This Test",
				"type along to Rick Astley's classic hit for 1 minute")
		{
			@Override
			public TypingLogic build() {
				return new StandardTypingLogic(LoopingTest.rickAstley);
			}
		};
		Builder<TypingLogic> stlTwoCities = new Builder<TypingLogic>(
				"Dicken's Typing Test",
				"type along to the introduction of a Tale of Two Cities for 1 minute")
		{
			@Override
			public TypingLogic build() {
				return new StandardTypingLogic(LoopingTest.taleOfTwoCities);
			}
		};
		OptionActionListener<TypingLogic> typingTests = new OptionActionListener<TypingLogic>("Typing Test Logic",stlDefault);
		typingTests.addOption(stlDefault);
		typingTests.addOption(stlRickAstley);
		typingTests.addOption(stlTwoCities);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Builder<DisplayComponent> graphDisplay = new Builder<DisplayComponent>(
				"Graph Progress Display",
				"graphs instantaneous WPM throughout the typing test"
				)
		{
			@Override
			public DisplayComponent build() {
				IntervalUpdater updater = new IntervalUpdater(1.0/60.0);
				ProgressDC progressDisplay = new ProgressDC(new GraphProgressDCPanel());
				updater.addUpdate(progressDisplay);
				updater.start();
				return progressDisplay;
			}
		};
		
		Builder<DisplayComponent> colorDisplay = new Builder<DisplayComponent>(
				"Color Progress Display",
				"displays instantaneous WPM as a color throughout the typing test"
				)
		{
			@Override
			public DisplayComponent build() {
				IntervalUpdater updater = new IntervalUpdater(1.0/60.0);
				ProgressDC progressDisplay = new ProgressDC(new ColorfulProgressDCPanel());
				updater.addUpdate(progressDisplay);
				updater.start();
				return progressDisplay;
			}
		};
		
		OptionActionListener<DisplayComponent> progressDisplays = new OptionActionListener<DisplayComponent>("Progress Display",graphDisplay);
		progressDisplays.addOption(graphDisplay);
		progressDisplays.addOption(colorDisplay);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Builder<DisplayComponent> lineScrolling = new Builder<DisplayComponent>(
				"Line Scrolling Word Display",
				"puts text into lines that disappear once the last word is finished"
				)
		{
			@Override
			public DisplayComponent build() {
				return new WordDisplayComponent2();
			}
		};
		
		Builder<DisplayComponent> horizontalScrolling = new Builder<DisplayComponent>(
				"Horizontal Scrolling Word Display",
				"removes words immediately after they are typed"
				)
		{
			@Override
			public DisplayComponent build() {
				return new WordDisplayComponent();
			}
		};
		
		Builder<DisplayComponent> smoothHorizontalScrolling = new Builder<DisplayComponent>(
				"Smooth Horizontal Scrolling Word Display",
				"removes words immediately after they are typed, and smoothly scrolls next word into place"
				)
		{
			@Override
			public DisplayComponent build() {
				IntervalUpdater updater = new IntervalUpdater(1.0/30.0);
				WordDisplayComponent1 wordDisplay = new WordDisplayComponent1();
				updater.addUpdate(wordDisplay);
				updater.start();
				return wordDisplay;
			}
		};
		
		OptionActionListener<DisplayComponent> wordDisplay = new OptionActionListener<DisplayComponent>("Word Display",lineScrolling);
		wordDisplay.addOption(lineScrolling);
		wordDisplay.addOption(horizontalScrolling);
		wordDisplay.addOption(smoothHorizontalScrolling);
		
		Menu m = new Menu(typingTests, progressDisplays, wordDisplay);
		m.show();
	}
}
