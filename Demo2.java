import java.util.Scanner;

public class Demo2 {

    public static void main(String[] args) {
        int[][] table = new int[16][16];
        createTable(table);
        int count = 0;

        Scanner input = new Scanner(System.in);

        //开始下棋
        while (true) {
            blackPlayer(table, input);
            //判断是否有人胜出
            if (checkRowWinner(table) == 1 ||
                    checkColumnWinner(table) == 1 ||
                    checkLeftSlashWinner(table) == 1 ||
                    checkRightSlashWinner(table) == 1) {
                System.out.println("黑方胜！");
                break;
            } else if (checkRowWinner(table) == 2 ||
                    checkColumnWinner(table) == 2 ||
                    checkLeftSlashWinner(table) == 2 ||
                    checkRightSlashWinner(table) == 2) {
                System.out.println("白方胜！");
                break;
            }
            if (count == 112) { //当count等于112时，此时棋盘上已经没有空位，无人胜出
                System.out.println("很遗憾，无人胜出！");
                break;
            }

            whitePlayer(table, input);
            if (checkRowWinner(table) == 1 ||
                    checkColumnWinner(table) == 1 ||
                    checkLeftSlashWinner(table) == 1 ||
                    checkRightSlashWinner(table) == 1) {
                System.out.println("黑方胜！");
                break;
            } else if (checkRowWinner(table) == 2 ||
                    checkColumnWinner(table) == 2 ||
                    checkLeftSlashWinner(table) == 2 ||
                    checkRightSlashWinner(table) == 2){
                System.out.println("白方胜！");
                break;
            }
            drawTable(table);
            count++;

        }
        drawTable(table);
    }

    /**
     * 生成棋盘并返回
     * @param table
     */
    private static void createTable(int[][] table) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (i == 0) {
                    table[i][j] = j;
                } else if (j == 0) {
                    table[i][j] = i;
                } else {
                    table[i][j] = 0;
                }
            }
        }
    }

    /**
     * 绘制棋盘
     * @param table
     */
    private static void drawTable(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                System.out.print(table[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 黑方下棋
     * @param table 将棋盘传入
     * @param input 传入可接收键盘输入的对象
     */
    private static void blackPlayer(int[][] table, Scanner input) {
        int x;
        int y;
        System.out.print("黑方，请输入你要下棋点的X坐标：");
        x = input.nextInt();
        System.out.print("黑方，请输入你要下棋点的Y坐标：");
        y = input.nextInt();
        if (x > 15 || x < 1 || y > 15 || y < 1) { //判断下棋点是否为空
            System.out.println("你输入的数字不在范围内！！！");
            blackPlayer(table, input);
        } else if (table[x][y] == 0){ //判断所写坐标是否在范围内
            table[x][y] = 1;
        } else {
            System.out.println("这个位置已经有旗子了，请重新下棋！");    //下棋点不为空，提示重下
            blackPlayer(table, input);
        }

    }

    /**
     * 白方下棋
     * @param table 将棋盘传入
     * @param input 传入可接收键盘输入的对象
     */
    private static void whitePlayer(int[][] table, Scanner input) {
        int x;
        int y;
        System.out.print("白方，请输入你要下棋点的X坐标：");
        x = input.nextInt();
        System.out.print("白方，请输入你要下棋点的Y坐标：");
        y = input.nextInt();
        if (x > 15 || x < 1 || y > 15 || y < 1) { //判断下棋点是否为空
            System.out.println("你输入的数字不在范围内！！！");
            whitePlayer(table, input);
        } else if (table[x][y] == 0){
            table[x][y] = 2;
        } else {
            System.out.println("这个位置已经有旗子了，请重新下棋！");
            whitePlayer(table, input);
        }
    }

    /**
     * 判断一行中有没有连续五个子的
     * @param table 传入棋盘
     * @return 判断结果
     */
    private static int checkRowWinner(int[][] table) {
        int result = 0;
        outer: for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 12; j++) {  //j不能超过11，不然会越界
                if (table[i][j] == table[i][j + 1] &&
                        table[i][j] == table[i][j + 2] &&
                        table[i][j] == table[i][j + 3] &&
                        table[i][j] == table[i][j + 4]) {
                    if (table[i][j] == 1) {
                        result = 1;
                        break outer;
                    } else if (table[i][j] == 2){
                        result = 2;
                        break outer;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 判断一列中有没有连续五个子的
     * @param table 传入棋盘
     * @return 判断结果
     */
    private static int checkColumnWinner(int[][] table) {
        int result = 0;
        outer: for (int i = 1; i < 12; i++) {   //i不能超过11，不然会越界
            for (int j = 1; j < 16; j++) {
                if (table[i][j] == table[i + 1][j] &&
                        table[i][j] == table[i + 2][j] &&
                        table[i][j] == table[i + 3][j] &&
                        table[i][j] == table[i + 4][j]) {
                    if (table[i][j] == 1) {
                        result = 1;
                        break outer;
                    } else if (table[i][j] == 2){
                        result = 2;
                        break outer;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 判断左斜线中有没有连续五子的
     * @param table 传入棋盘
     * @return 判断结果
     */
    private static int checkLeftSlashWinner(int[][] table) {
        int result = 0;
        outer: for (int i = 1; i < 12; i++) {   //i不能超过11，不然会越界
            for (int j = 1; j < 12; j++) {  //j不能超过11，不然会越界
                if (table[i][j] == table[i + 1][j + 1] &&
                        table[i][j] == table[i + 2][j + 2] &&
                        table[i][j] == table[i + 3][j + 3] &&
                        table[i][j] == table[i + 4][j + 4]) {
                    if (table[i][j] == 1) {
                        result = 1;
                        break outer;
                    } else if (table[i][j] == 2){
                        result = 2;
                        break outer;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 判断右斜线有没有连续五子的
     * @param table 传入棋盘
     * @return 判断结果
     */
    private static int checkRightSlashWinner(int[][] table) {
        int result = 0;
        outer: for (int i = 11; i > 0; i--) {   //i不能超过11，不然会越界
            for (int j = 15; j > 4; j--) {  //j不能小于5，不然会越界
                if (table[i][j] == table[i + 1][j - 1] &&
                        table[i][j] == table[i + 2][j - 2] &&
                        table[i][j] == table[i + 3][j - 3] &&
                        table[i][j] == table[i + 4][j - 4]) {
                    if (table[i][j] == 1) {
                        result = 1;
                        break outer;
                    } else if (table[i][j] == 2){
                        result = 2;
                        break outer;
                    }
                }
            }
        }
        return result;
    }
}
