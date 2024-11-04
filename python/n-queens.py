class NQueens:
    
    def __init__(self, n):
        self.n = n
        self.board = [[0] * n for _ in range(n)]  # initialize the chess board

    def print_board(self):  # print the chess board
        print()
        for row in self.board:
            print(' '.join('Q' if cell == 1 else '.' for cell in row))
        print()

    def is_safe(self, row, col):  # check if placing a queen at a specific position is safe
        for i in range(col):
            if self.board[row][i] == 1:
                return False

        for i, j in zip(range(row, -1, -1), range(col, -1, -1)):
            if self.board[i][j] == 1:
                return False

        for i, j in zip(range(row, self.n), range(col, -1, -1)):
            if self.board[i][j] == 1:
                return False

        return True

    def solve_backtracking(self, col): 
        if col >= self.n:
            return True

        for i in range(self.n):
            if self.is_safe(i, col):
                self.board[i][col] = 1

                if self.solve_backtracking(col + 1):
                    return True

                self.board[i][col] = 0  # backtrack

        return False

    def backtracking(self): 
        if not self.solve_backtracking(0):
            print("\nNo solution!")
            return False

        self.print_board()
        return True

N = int(input("\nEnter the size of the chessboard (N): "))

if N < 0 or N > 8:
    print("\nInvalid board size. N should be between 0 and 8.")
else:
    q = NQueens(N)
    q.backtracking()  # Call only the backtracking method
