//package game;
//
//
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Ellipse;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.shape.Shape;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
///**
// * @author ChatGPT
// * @desc 中国象棋
// * @since 2023-05-03
// */
//public class ChineseChess extends Application {
//
//
//    private final int ROWS = 10; // 棋盘行数
//    private final int COLS = 9; // 棋盘列数
//    private final int TILE_SIZE = 60; // 棋盘格子大小
//
//    private Shape[][] pieces; // 棋子形状
//
//    public void start(Stage primaryStage) {
//        // 创建棋盘形状
//        Shape board = createBoard();
//
//        // 创建棋子形状
//        pieces = new Shape[ROWS][COLS];
//        createPieces();
//
//        // 创建主场景
//        Group root = new Group(board);
//        for (int row = 0; row < ROWS; row++) {
//            for (int col = 0; col < COLS; col++) {
//                Shape piece = pieces[row][col];
//                if (piece != null) {
//                    root.getChildren().add(piece);
//                }
//            }
//        }
//
//        Scene scene = new Scene(root, COLS * TILE_SIZE, ROWS * TILE_SIZE);
//
//        primaryStage.setTitle("中国象棋");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    // 创建棋盘形状
//    private Shape createBoard() {
//        Shape board = new Rectangle(COLS * TILE_SIZE, ROWS * TILE_SIZE);
//        board.setFill(Color.WHITE);
//
//        for (int row = 0; row < ROWS; row++) {
//            for (int col = 0; col < COLS; col++) {
//                Shape tile = new Rectangle(TILE_SIZE, TILE_SIZE);
//                tile.setFill((row + col) % 2 == 0 ? Color.valueOf("#c97d50") : Color.valueOf("#f5d5b5"));
//                tile.setTranslateX(col * TILE_SIZE);
//                tile.setTranslateY(row * TILE_SIZE);
//                board = Shape.union(board, tile);
//            }
//        }
//
//        return board;
//    }
//
//    // 创建棋子形状
//    private void createPieces() {
//        // 初始化红方棋子
//        pieces[0][0] = createPiece(PieceType.ROOK, true);
//        pieces[0][1] = createPiece(PieceType.HORSE, true);
//        pieces[0][2] = createPiece(PieceType.ELEPHANT, true);
//        pieces[0][3] = createPiece(PieceType.ADVISOR, true);
//        pieces[0][4] = createPiece(PieceType.GENERAL, true);
//        pieces[0][5] = createPiece(PieceType.ADVISOR, true);
//        pieces[0][6] = createPiece(PieceType.ELEPHANT, true);
//        pieces[0][7] = createPiece(PieceType.HORSE, true);
//        pieces[0][8] = createPiece(PieceType.ROOK, true);
//        pieces[2][1] = createPiece(PieceType.CANNON, true);
//        pieces[2][7] = createPiece(PieceType.CANNON, true);
//        pieces[3][0] = createPiece(PieceType.SOLDIER, true);
//        pieces[3][2] = createPiece(PieceType.SOLDIER, true);
//        pieces[3][4] = createPiece(PieceType.SOLDIER, true);
//        pieces[3][6] = createPiece(PieceType.SOLDIER, true);
//        pieces[3][8] = createPiece(PieceType.SOLDIER, true);
//
//        // 初始化黑方棋子
//        pieces[9][0] = createPiece(PieceType.ROOK, false);
//        pieces[9][1] = createPiece(PieceType.HORSE, false);
//        pieces[9][2] = createPiece(PieceType.ELEPHANT, false);
//        pieces[9][3] = createPiece(PieceType.ADVISOR, false);
//        pieces[9][4] = createPiece(PieceType.GENERAL, false);
//        pieces[9][5] = createPiece(PieceType.ADVISOR, false);
//        pieces[9][6] = createPiece(PieceType.ELEPHANT, false);
//        pieces[9][7] = createPiece(PieceType.HORSE, false);
//        pieces[9][8] = createPiece(PieceType.ROOK, false);
//        pieces[7][1] = createPiece(PieceType.CANNON, false);
//        pieces[7][7] = createPiece(PieceType.CANNON, false);
//        pieces[6][0] = createPiece(PieceType.SOLDIER, false);
//        pieces[6][2] = createPiece(PieceType.SOLDIER, false);
//        pieces[6][4] = createPiece(PieceType.SOLDIER, false);
//        pieces[6][6] = createPiece(PieceType.SOLDIER, false);
//        pieces[6][8] = createPiece(PieceType.SOLDIER, false);
//    }
//
//    // 创建一个棋子形状
//    private Shape createPiece(PieceType type, boolean isRed) {
//        Shape piece;
//        switch (type) {
//            case ROOK:
//                piece = new Ellipse(TILE_SIZE * 0.4, TILE_SIZE * 0.5);
//                piece.setFill(Color.WHITE);
//                piece.setStroke(Color.BLACK);
//                break;
//            case HORSE:
//                piece = new Shape(
//                        new Ellipse(TILE_SIZE * 0.4, TILE_SIZE * 0.5, TILE_SIZE * 0.1, TILE_SIZE * 0.3),
//                        new Rectangle(TILE_SIZE * 0.3, TILE_SIZE * 0.3, TILE_SIZE * 0.4, TILE_SIZE * 0.2),
//                        new Rectangle(TILE_SIZE * 0.4, TILE_SIZE * 0.1, TILE_SIZE * 0.2, TILE_SIZE * 0.2)
//                );
//                piece.setFill(Color.WHITE);
//                piece.setStroke(Color.BLACK);
//                break;
//            case ELEPHANT:
//                piece = new Group(
//                        new Ellipse(TILE_SIZE * 0.4, TILE_SIZE * 0.5, TILE_SIZE * 0.1, TILE_SIZE * 0.3),
//                        new Rectangle(TILE_SIZE * 0.3, TILE_SIZE * 0.3, TILE_SIZE * 0.4, TILE_SIZE * 0.2),
//                        new Rectangle(TILE_SIZE * 0.4, TILE_SIZE * 0.1, TILE_SIZE * 0.2, TILE_SIZE * 0.2),
//                        new Circle(TILE_SIZE * 0.15)
//                );
//                piece.setFill(Color.WHITE);
//                piece.setStroke(Color.BLACK);
//                break;
//            case ADVISOR:
//                piece = new Group(
//                        new Ellipse(TILE_SIZE * 0.4, TILE_SIZE * 0.5),
//                        new Text(TILE_SIZE * 0.4, TILE_SIZE * 0.5, "士")
//                );
//                piece.setFill(Color.WHITE);
//                piece.setStroke(Color.BLACK);
//                break;
//            case GENERAL:
//                piece = new Group(
//                        new Ellipse(TILE_SIZE * 0.4, TILE_SIZE * 0.5),
//                        new Text(TILE_SIZE * 0.4, TILE_SIZE * 0.5, "将")
//                );
//                piece.setFill(Color.WHITE);
//                piece.setStroke(Color.BLACK);
//                break;
//            case CANNON:
//                piece = new Group(
//                        new Ellipse(TILE_SIZE * 0.4, TILE_SIZE * 0.5),
//                        new Rectangle(TILE_SIZE * 0.3, TILE_SIZE * 0.2, TILE_SIZE * 0.4, TILE_SIZE * 0.1),
//                        new Rectangle(TILE_SIZE * 0.4, TILE_SIZE * 0.1, TILE_SIZE * 0.2, TILE_SIZE * 0.2)
//                );
//                piece.setFill(Color.WHITE);
//                piece.setStroke(Color.BLACK);
//                break;
//            case SOLDIER:
//                piece = new Group(
//                        new Ellipse(TILE_SIZE * 0.4, TILE_SIZE * 0.5),
//                        new Rectangle(TILE_SIZE * 0.2, TILE_SIZE * 0.4, TILE_SIZE * 0.6, TILE_SIZE * 0.1),
//                        new Circle(TILE_SIZE * 0.15, TILE_SIZE * 0.7, TILE_SIZE * 0.15)
//                );
//                piece.setFill(Color.WHITE);
//                piece.setStroke(Color.BLACK);
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid piece type: " + type);
//        }
//
//// 设置棋子颜色
//        if (isRed) {
//            piece.setFill(Color.RED);
//        }
//
//        return piece;
//
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
