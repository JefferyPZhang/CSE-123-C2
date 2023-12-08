// Jeffery Zhang
// TA: Jake Page
// CSE 123
// Due: November 8th, 2023
// C2: Mondrian Art

// This is a class to represent a self-painting Mondrian Art canvas.

import java.awt.*;

public class Mondrian {
    private final Color[] basicColors;

    // Constructs a new (self-painting) Mondrian Art canvas (B).
    public Mondrian() {
        basicColors = new Color[] {Color.RED, Color.WHITE, Color.CYAN, Color.YELLOW};
    } 

    // Paints a basic Mondrian pattern on the canvas (B),
    // given the "canvas" or array of colored pixels (P).
    public void paintBasicMondrian(Color[][] pixels) {
        findBlocks(pixels, 0, 0, pixels.length, pixels[0].length, false);
        finishBorders(pixels);
    }

    // Paints a complex Mondrian pattern on the canvas (B),
    // given the "canvas" or array of colored pixels (P).
    public void paintComplexMondrian(Color[][] pixels) {
        findBlocks(pixels, 0, 0, pixels.length, pixels[0].length, true);
        finishBorders(pixels);
    }

    // Recursively finds a chosen block to color in,
    // given certain requirements of the block (B).
    // Parameters are the "canvas" or the array of colored pixels, 
    // integer indices to keep track of the top, left, bottom, 
    // right boundaries of the canvas search area, and a boolean to 
    // keep track of whether the paint method is complex (P).
    private void findBlocks(Color[][] pixels, int top, int left, 
                            int bottom, int right, boolean isComplex) {
        int length = bottom - top;
        int width = right - left;
        boolean canSplitHori = length >= pixels.length / 4;
        boolean canSplitVert = width >= pixels[0].length / 4;
        if (canSplitHori && canSplitVert) {
            int horiSplit = (int) (Math.random() * (length - 2)) + 1;
            int vertSplit = (int) (Math.random() * (width - 2)) + 1;
            findBlocks(pixels, top, left, bottom - horiSplit, right - vertSplit, isComplex);
            findBlocks(pixels, top, right - vertSplit, bottom - horiSplit, right, isComplex);
            findBlocks(pixels, bottom - horiSplit, left, bottom, right - vertSplit, isComplex);
            findBlocks(pixels, bottom - horiSplit, right - vertSplit, bottom, right, isComplex);
        } else if (canSplitHori) {
            int horiSplit = (int) (Math.random() * (length - 2)) + 1;
            findBlocks(pixels, top, left, bottom - horiSplit, right, isComplex);
            findBlocks(pixels, bottom - horiSplit, left, bottom, right, isComplex);
        } else if (canSplitVert) {
            int vertSplit = (int) (Math.random() * (width - 2)) + 1;
            findBlocks(pixels, top, right - vertSplit, bottom, right, isComplex);
            findBlocks(pixels, top, left, bottom, right - vertSplit, isComplex);
        }  else {
            if (isComplex) {
                paintComplex(pixels, top, left, bottom, right);
            } else {
                paintBasic(pixels, top, left, bottom, right);
            }
        }
    }

    // Paints the canvas given a set of four colors (B).
    // Parameters are the "canvas" or the array of colored pixels, 
    // integer indices to keep track of the top, left, bottom, 
    // right boundaries of the canvas paint area (P).
    private void paintBasic(Color[][] pixels, int top, 
                            int left, int bottom, int right) {
        int randomIndex = (int) (Math.random() * basicColors.length);
        for (int i = top; i < bottom - 1; i++) {
            for (int j = left; j < right - 1; j++) {
                pixels[i][j] = basicColors[randomIndex];
            }
        }
    }

    // Paints the canvas with a diagonal gradient of cyan/red (B).
    // Parameters are the "canvas" or the array of colored pixels, 
    // integer indices to keep track of the top, left, bottom, 
    // right boundaries of the canvas paint area (P).
    private void paintComplex(Color[][] pixels, int top, 
                              int left, int bottom, int right) {
        int horiBlockCenter = (left + right) / 2;
        int vertBlockCenter = (top + bottom) / 2;
        int red = 255;
        int green = 255;
        int blue = 255;
        red = Math.min(Math.max(red - horiBlockCenter / 3, 20), 235);
        green = Math.min(Math.max(green - vertBlockCenter / 3, 20), 235);
        blue = Math.min(Math.max(blue - vertBlockCenter / 3, 20), 235);
        Color color = new Color(red, green, blue);
        for (int i = top; i < bottom - 1; i++) {
            for (int j = left; j < right - 1; j++) {
                pixels[i][j] = color;
            }
        }
    }

    // Due to the behavior of the recursive painting methods,
    // the very top and left borders are not black. This method fixes
    // that (B), given the the "canvas" or the array of colored pixels
    // as a parameter (P).
    private void finishBorders(Color[][] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i][0] = Color.BLACK;
        }
        for (int i = 0; i < pixels[0].length - 1; i++) {
            pixels[0][i] = Color.BLACK;
        }
    }
}
