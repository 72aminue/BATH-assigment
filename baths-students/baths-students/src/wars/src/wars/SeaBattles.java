package wars;

public class SeaBattles {
    private static final int BOARD_SIZE = 10;
    private char[][] playerBoard;
    private char[][] enemyBoard;
    private int playerShipsRemaining;
    private int enemyShipsRemaining;
    private boolean isGameOver;

    public SeaBattles() {
        playerBoard = new char[BOARD_SIZE][BOARD_SIZE];
        enemyBoard = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoards();
        playerShipsRemaining = 5; // Default number of ships
        enemyShipsRemaining = 5;
        isGameOver = false;
    }

    private void initializeBoards() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                playerBoard[i][j] = '~'; // Water
                enemyBoard[i][j] = '~';
            }
        }
    }

    public boolean placeShip(int x, int y, int length, boolean isHorizontal) {
        if (x < 0 || y < 0 || x >= BOARD_SIZE || y >= BOARD_SIZE) {
            return false;
        }

        // Check if ship placement is valid
        if (isHorizontal) {
            if (x + length > BOARD_SIZE) return false;
            for (int i = x; i < x + length; i++) {
                if (playerBoard[i][y] != '~') return false;
            }
            // Place the ship
            for (int i = x; i < x + length; i++) {
                playerBoard[i][y] = 'S';
            }
        } else {
            if (y + length > BOARD_SIZE) return false;
            for (int i = y; i < y + length; i++) {
                if (playerBoard[x][i] != '~') return false;
            }
            // Place the ship
            for (int i = y; i < y + length; i++) {
                playerBoard[x][i] = 'S';
            }
        }
        return true;
    }

    public boolean attack(int x, int y) {
        if (x < 0 || y < 0 || x >= BOARD_SIZE || y >= BOARD_SIZE || isGameOver) {
            return false;
        }

        if (enemyBoard[x][y] == 'S') {
            enemyBoard[x][y] = 'X'; // Hit
            enemyShipsRemaining--;
            checkGameOver();
            return true;
        } else if (enemyBoard[x][y] == '~') {
            enemyBoard[x][y] = 'O'; // Miss
            return true;
        }
        return false; // Already attacked this position
    }

    public void enemyAttack(int x, int y) {
        if (x < 0 || y < 0 || x >= BOARD_SIZE || y >= BOARD_SIZE || isGameOver) {
            return;
        }

        if (playerBoard[x][y] == 'S') {
            playerBoard[x][y] = 'X'; // Hit
            playerShipsRemaining--;
            checkGameOver();
        } else if (playerBoard[x][y] == '~') {
            playerBoard[x][y] = 'O'; // Miss
        }
    }

    private void checkGameOver() {
        if (playerShipsRemaining == 0 || enemyShipsRemaining == 0) {
            isGameOver = true;
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getPlayerShipsRemaining() {
        return playerShipsRemaining;
    }

    public int getEnemyShipsRemaining() {
        return enemyShipsRemaining;
    }

    public char[][] getPlayerBoard() {
        return playerBoard.clone();
    }

    public char[][] getEnemyBoard() {
        return enemyBoard.clone();
    }

    public void printBoards() {
        System.out.println("Player Board:");
        printBoard(playerBoard);
        System.out.println("\nEnemy Board:");
        printBoard(enemyBoard);
    }

    private void printBoard(char[][] board) {
        System.out.print("  ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
} 