//package gui.yfiles;
//
//import javax.swing.*;
//import java.awt.*;
//
///**
// * @author pengzh
// * @desc
// * @class SimpleGraphViewer1
// * @since 2021-09-26
// */
//public class SimpleGraphViewer1 {
//    JFrame frame;
//    /** The yFiles view component that displays (and holds) the graph. */
//    Graph2DView view;
//
//    public SimpleGraphViewer1(Dimension size, String title) {
//        view = createGraph2DView();
//        frame = createApplicationFrame(size, title, view);
//    }
//
//    private Graph2DView createGraph2DView() {
//        Graph2DView view = new Graph2DView();
//        return view;
//    }
//
//    /** Creates a JFrame that will show the demo graph. */
//    private JFrame createApplicationFrame(Dimension size, String title,
//                                          JComponent view) {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setPreferredSize(size);
//        // Add the given view to the panel.
//        panel.add(view, BorderLayout.CENTER);
//        JFrame frame = new JFrame(title);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getRootPane().setContentPane(panel);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        return frame;
//    }
//
//    public void show() { frame.setVisible(true); }
//
//    public static void main(String[] args) {
//        SimpleGraphViewer1 sgv =
//                new SimpleGraphViewer1(new Dimension(400, 200),
//                        SimpleGraphViewer1.class.getName());
//        sgv.show();
//    }
//
//}
