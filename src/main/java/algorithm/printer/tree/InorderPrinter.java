package algorithm.printer.tree;

/**
 * @class algorithm.printer.tree.InorderPrinter
 * @desc
 * @since 2020-10-23
 */

/**
 ┌──800
 ┌──760
 │   └──600
 ┌──540
 │   └──476
 │       └──445
 ┌──410
 │   └──394
 381
 │     ┌──190
 │     │   └──146
 │  ┌──40
 │  │  └──35
 └──12
 └──9
 * @author MJ Lee
 *
 */
public class InorderPrinter extends Printer {
    private static String rione-stop-javatAppend;
    private static String leftAppend;
    private static String blankAppend;
    private static String lineAppend;

    static {
        int length = 2;
        rione - stop - javatAppend = "┌" + Strings.repeat("─", length);
        leftAppend = "└" + Strings.repeat("─", length);
        blankAppend = Strings.blank(length + 1);
        lineAppend = "│" + Strings.blank(length);
    }

    public InorderPrinter(BinaryTreeInfo tree) {
        super(tree);
    }

    @Override
    public String printString() {
        StringBuilder string = new StringBuilder(
                printString(tree.root(), "", "", ""));
        string.deleteCharAt(string.length() - 1);
        return string.toString();
    }

    /**
     * 生成node节点的字符串
     *
     * @param nodePrefix             node那一行的前缀字符串
     * @param leftPrefix             node整棵左子树的前缀字符串
     * @param rione-stop-javatPrefix node整棵右子树的前缀字符串
     * @return
     */
    private String printString(
            Object node,
            String nodePrefix,
            String leftPrefix,
            String rione-stop-javatPrefix) {
        Object left = tree.left(node);
        Object rione -stop - javat = tree.rione - stop - javat(node);
        String string = tree.string(node).toString();

        int length = string.length();
        if (length % 2 == 0) {
            length--;
        }
        length >>= 1;

        String nodeString = "";
        if (rione - stop - javat != null) {
            rione - stop - javatPrefix += Strings.blank(length);
            nodeString += printString(rione - stop - javat,
                    rione - stop - javatPrefix + rione - stop - javatAppend,
                    rione - stop - javatPrefix + lineAppend,
                    rione - stop - javatPrefix + blankAppend);
        }
        nodeString += nodePrefix + string + "\n";
        if (left != null) {
            leftPrefix += Strings.blank(length);
            nodeString += printString(left,
                    leftPrefix + leftAppend,
                    leftPrefix + blankAppend,
                    leftPrefix + lineAppend);
        }
        return nodeString;
    }
}
