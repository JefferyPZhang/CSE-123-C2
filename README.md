**Assignment Spec:**

Program Behavior:
You will implement a program to generate random artwork in the style of Mondrian. Your random artwork will start with a blank canvas of a given width and height and should repeatedly break the canvas into smaller and smaller regions until the regions are below a certain size. The specifics of this algorithm should be as follows:

If the region being considered is at least one-fourth the height of the full canvas and at least one-fourth the width of the full canvas, split it into four smaller regions by choosing one vertical and one horizontal dividing line at random.

If the region being considered is at least one-fourth the height of the full canvas, split it into two smaller regions by choosing a horizontal dividing line at random.

If the region being considered is at least one-fourth the width of the full canvas, split it into two smaller regions by choosing a vertical dividing line at random.

If the region being considered is smaller than one-fourth the height of the full canvas and smaller than one-fourth the width of the full canvas, do not split the region.

Any time a region is split, the dividing line(s) should be chosen at random to be within the bounds of the region.

Once a region is below a certain size, it should be filled in with a color chosen randomly from red, yellow, cyan, and white. When filling a region, leave a one-pixel border around the edge uncolored -- this will give the appearance of black lines separating the colored regions.
