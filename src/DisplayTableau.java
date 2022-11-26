public class DisplayTableau
{
    public String createTableau(char[][] grid)  // main function, returns tableau as string
    {
        StringBuilder builder = new StringBuilder();
        String motif = "+-----";

        builder.append(printUpperLine(grid));
        builder.append(printDashedLine(grid, motif));
        builder.append(printGrid(grid, motif));

        return builder.toString();
    }

    private StringBuilder printUpperLine(char[][] grid)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("       ");
        for (int i = 0; i < grid.length; i++)
        {
            builder.append( i);
            builder.append("     ");
        }

        builder.append(System.lineSeparator()); // for java --> "\r\n"
        return builder;
    }

    private StringBuilder printGrid(char[][] grid, String motif)
    {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < grid.length; i++)
        {
            builder.append(String.format("%3d", i ));
            builder.append(" ");
            for (int j = 0; j < grid[i].length; j++)
            {
                builder.append("+  ");
                builder.append(grid[i][j]);
                builder.append("  ");
            }

            builder.append("+");
            builder.append(System.lineSeparator());  // for java --> "\r\n"
            builder.append(printDashedLine(grid, motif));
        }

        return builder;
    }

    private StringBuilder printDashedLine(char[][] grid, String motif)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("    ");
        for (int i = 0; i < grid.length; i++)
        {
            builder.append(motif);
        }

        builder.append("+");
        builder.append(System.lineSeparator());  // for java --> "\r\n"

        return builder;
    }
}