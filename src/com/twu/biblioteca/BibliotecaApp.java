package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {
    private Library lib;
    private Menu menu;
    private boolean run = true;

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        System.out.print(app.getWelcomeMessage());

        while(app.isRunning()) {
            System.out.print(app.viewMenuOptions());

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            System.out.println(app.getMenuOptionResult(input));
        }

    }

    public BibliotecaApp() {
        this.lib = new Library();
        this.menu = Menu.defaultMenu();

    }

    public String getWelcomeMessage() {
        return "Welcome to Biblioteca\n";
    }

    public String getMenuOptionResult(String option) {
        if (option.equals(Menu.LIST_BOOKS)) {
            String result = "";
            for (Book book : lib.getBookList()) {
                result += book.getDetails() + "\n";
            }
            return result;
        } else if (option.matches(Menu.CHECKOUT + "[A-Za-z ]*")) {
            String title = option.substring(Menu.CHECKOUT.length() + 1);
            return this.checkoutBook(title);
        } else if (option.matches(Menu.RETURN + "[A-Za-z ]*")) {
            String title = option.substring(Menu.RETURN.length() + 1);
            return this.returnBook(title);
        } else if (option.equals(Menu.QUIT)) {
            run = false;
            return "";
        }
        return "Select a valid option!";
    }

    public boolean isRunning() {
        return run;
    }

    public String viewMenuOptions() {
        String result = "Options:\n";
        int counter = 1;
        for (String option: menu.getMenuOptions()) {
            result += counter + ". " +option + "\n";
            counter++;
        }
        return result;
    }

    private String checkoutBook(String title) {
        if (lib.checkoutBook(title)) {
            return "Thank you! Enjoy the book";
        } else {
            return "That book is not available.";
        }
    }

    private String returnBook(String title) {
        if (lib.returnBook(title)) {
            return "Thank you for returning the book.";
        } else {
            return "That is not a valid book to return.";
        }
    }
}
