# Infix to Postfix Converter

A Java application that converts mathematical expressions from **Infix notation** to **Postfix notation** using the **Stack** data structure.

## Overview

Infix expressions are the standard way mathematical expressions are written, where operators are placed between operands.

For example:

```text
A + B * C
````

Postfix notation places operators after their operands:

```text
A B C * +
```

This project demonstrates how a **Stack** can be used to handle operators, precedence, and parentheses during the conversion process.

## Features

* Convert Infix expressions to Postfix expressions.
* Handle arithmetic operators.
* Handle operator precedence.
* Handle parentheses.
* Use a Stack to manage operators during conversion.
* Provide a graphical user interface using JavaFX.

## Example

### Infix Expression

```text
A + B * C
```

### Postfix Expression

```text
A B C * +
```

Another example:

```text
Infix:
(A + B) * C

Postfix:
A B + C *
```

## Data Structure

The main data structure used in this project is a **Stack**.

During conversion:

1. Operands are added directly to the postfix expression.
2. Operators are temporarily stored in the Stack.
3. Operator precedence is used to determine when operators should be removed from the Stack.
4. Parentheses are handled using the Stack.
5. Remaining operators are added to the postfix expression after processing the expression.

### Example

For:

```text
A + B * C
```

The multiplication operator has higher precedence than addition, so the result becomes:

```text
A B C * +
```

## Project Structure

```text
infix-to-postfix-converter/
├── src/
│   └── main/
│       ├── java/
│       └── resources/
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Main Components

### StackCharacter

A custom Stack implementation used to store and manipulate operators during the conversion process.

### JavaFX Interface

The project includes a graphical user interface that allows the user to enter an Infix expression and convert it into Postfix notation.

## Technologies

* Java
* JavaFX
* Maven
* Object-Oriented Programming
* Data Structures
* Stack

## Purpose

This project was developed as a college Data Structures project to practice implementing and using the **Stack** data structure in a practical application.

It demonstrates how stacks can be applied to expression parsing and conversion between different mathematical notations.

## Author

**Nour Tarek**

**ملاحظة صغيرة:** أنا متعمد ما أقولش في الـ README إن المشروع بيعمل *evaluation* للـ postfix أو بيدعم operators معينة بالاسم، لأننا عايزين الـ README يصف **اللي موجود فعلًا في الكود** وليس نفترض features غير مؤكدة.
