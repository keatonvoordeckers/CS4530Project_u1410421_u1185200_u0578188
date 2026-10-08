package com.example.drawingapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented tests for the Drawing Application, which will execute on an Android device.
 * Used ChatGPT to generate some UI tests.
 * Updated: 10/7/2026
 */
@RunWith(AndroidJUnit4::class)
class DrawingAppInstrumentedTests {
    // UI Tests
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun splashScreenDisplaysText() {
        composeTestRule
            .onNodeWithText("Splash Screen")
            .assertIsDisplayed()
    }

    @Test
    fun canvasScreenDisplaysTitle() {
        waitForSplashScreenToFinish()

        composeTestRule
            .onNodeWithText("Canvas Screen")
            .assertIsDisplayed()
    }

    @Test
    fun canvasScreenDisplaysPenButton() {
        waitForSplashScreenToFinish()

        composeTestRule
            .onNodeWithText("Pen")
            .assertIsDisplayed()
    }

    @Test
    fun penScreenDisplaysTitle() {
        waitForSplashScreenToFinish()
        composeTestRule
            .onNodeWithText("Pen")
            .performClick()

        composeTestRule
            .onNodeWithText("Pen Screen")
            .assertIsDisplayed()
    }

    @Test
    fun penScreenDisplaysDefaultSizeOf10() {
        waitForSplashScreenToFinish()

        composeTestRule
            .onNodeWithText("Pen")
            .performClick()

        composeTestRule
            .onNodeWithText("Size: 10")
            .assertIsDisplayed()
    }

    @Test
    fun penScreenPlusButtonIncreasesSizeBy5() {
        waitForSplashScreenToFinish()

        composeTestRule
            .onNodeWithText("Pen")
            .performClick()

        composeTestRule
            .onNodeWithText("+")
            .performClick()

        composeTestRule
            .onNodeWithText("Size: 15")
            .assertIsDisplayed()
    }

    @Test
    fun penScreenMinusButtonDecreasesSizeBy5() {
        waitForSplashScreenToFinish()

        composeTestRule
            .onNodeWithText("Pen")
            .performClick()

        composeTestRule
            .onNodeWithText("-")
            .performClick()

        composeTestRule
            .onNodeWithText("Size: 5")
            .assertIsDisplayed()
    }

    @Test
    fun penScreenDisplaysColorButtons() {
        waitForSplashScreenToFinish()

        composeTestRule
            .onNodeWithText("Pen")
            .performClick()

        composeTestRule
            .onNodeWithText("Black")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Red")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Green")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Blue")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Yellow")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Magenta")
            .assertIsDisplayed()
    }

    @Test
    fun penScreenDisplaysBrushButtons() {
        waitForSplashScreenToFinish()

        composeTestRule
            .onNodeWithText("Pen")
            .performClick()

        composeTestRule
            .onNodeWithText("Line")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Circle")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Rectangle")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Triangle")
            .assertIsDisplayed()
    }

    @Test
    fun penScreenCircleButtonChangesBrush() {
        waitForSplashScreenToFinish()

        composeTestRule
            .onNodeWithText("Pen")
            .performClick()

        composeTestRule
            .onNodeWithText("Circle")
            .performClick()

        composeTestRule
            .onNodeWithText("Brush: CIRCLE")
            .assertIsDisplayed()
    }

    @Test
    fun penScreenRectangleButtonChangesBrush() {
        waitForSplashScreenToFinish()

        composeTestRule
            .onNodeWithText("Pen")
            .performClick()

        composeTestRule
            .onNodeWithText("Rectangle")
            .performClick()

        composeTestRule
            .onNodeWithText("Brush: RECTANGLE")
            .assertIsDisplayed()
    }

    @Test
    fun penScreenTriangleButtonChangesBrush() {
        waitForSplashScreenToFinish()

        composeTestRule
            .onNodeWithText("Pen")
            .performClick()

        composeTestRule
            .onNodeWithText("Triangle")
            .performClick()

        composeTestRule
            .onNodeWithText("Brush: TRIANGLE")
            .assertIsDisplayed()
    }

    @Test
    fun penScreenCanvasButtonIsDisplayed() {
        waitForSplashScreenToFinish()

        composeTestRule
            .onNodeWithText("Pen")
            .performClick()

        composeTestRule
            .onNodeWithText("Canvas")
            .assertIsDisplayed()
    }

    @Test
    fun canvasScreenToPenScreenAndBack() {
        waitForSplashScreenToFinish()

        composeTestRule
            .onNodeWithText("Start Drawing")
            .performClick()

        composeTestRule
            .onNodeWithText("Canvas Screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Pen")
            .performClick()

        composeTestRule
            .onNodeWithText("Pen Screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Canvas")
            .performClick()

        composeTestRule
            .onNodeWithText("Canvas Screen")
            .assertIsDisplayed()
    }

    private fun waitForSplashScreenToFinish() {
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("splash_screen")
                .fetchSemanticsNodes()
                .isEmpty()
        }

        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("canvas_screen")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
    }
}