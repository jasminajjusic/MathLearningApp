# Math Learning App

**Math Learning App** is a Java desktop application designed for elementary school students to practice basic math skills. It is built using **Java** and **JavaFX** for a graphical user interface.

---

## Features

- **User Login:** Students enter their name before starting the game. Validation ensures that the game cannot start without a name.
- **Math Exercises:**
  - 5 randomly generated addition and subtraction problems with two-digit numbers.
  - 5 multiplication problems using single-digit numbers.
  - For subtraction, the program ensures the first number is larger than the second.
  - For addition, the sum of numbers does not exceed 100.
- **Timer:** A 60-second countdown for solving all 10 problems.
- **Score Calculation:** Displays the number of correct answers and percentage after time expires or when the user submits early.
- **Navigation:** Users can return to the start screen at any time to restart the game.

---

## How It Works

1. **Start Screen:** The user enters their name and clicks "Start Game".
2. **Game Window:** 
   - 10 math problems appear (5 addition/subtraction, 5 multiplication).
   - Users input their answers in the text fields next to each problem.
   - A timer counts down from 60 seconds.
3. **Submit:** Clicking "Submit" or waiting for the timer to expire calculates the score.
4. **Results:** Shows the score in the format `X/10 (Y%)` along with a congratulatory message.

---

## Technologies Used

- Java 11+
- JavaFX (for GUI)
- Random number generation for problem creation






