package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String Pink = "\u001B[95m";
        String reset = "\u001B[0m";
        String softPink = "\u001B[95m";

        BankAccount ba = new BankAccount();
        Scanner scn = new Scanner(System.in); // вынесли сюда

        while (true) {

            int choice;

            System.out.println(Pink + "══════════════════════════════════════════════" + reset);
            System.out.println(Pink + "             БАНКОВСКАЯ СИСТЕМА              " + reset);
            System.out.println(Pink + "══════════════════════════════════════════════" + reset);
            System.out.println("1. Показать счета");
            System.out.println("2. Пополнить счет");
            System.out.println("3. Снять деньги");
            System.out.println("4. Перевод");
            System.out.println("5. Заблокировать счет");
            System.out.println("6. Разблокировать счет");
            System.out.println("0. Выход");
            System.out.print(softPink + "Выберите действие: " + reset);

            choice = scn.nextInt();
            scn.nextLine(); // очистка буфера
            System.out.println();

            switch (choice) {

                case 1:
                    System.out.println("Список счетов:");
                    System.out.println(ba.accountNumber1 + " - " + ba.ownerName1 +
                            " - Баланс: " + ba.balanceUser1 + " - " + ba.isBlocked1);
                    System.out.println(ba.accountNumber2 + " - " + ba.ownerName2 +
                            " - Баланс: " + ba.balanceUser2 + " - " + ba.isBlocked2);
                    break;

                case 2:
                    System.out.print("Введите номер счета: ");
                    String userPrint3 = scn.nextLine();

                    System.out.print("Введите сумму пополнения: ");
                    double userPrint4 = scn.nextDouble();
                    scn.nextLine();

                    try {
                        if (userPrint3.equals(ba.accountNumber1)) {

                            if (userPrint4 <= 0)
                                throw new Exception("Сумма должна быть больше 0.");
                            if (ba.isBlocked1)
                                throw new Exception("Счет заблокирован.");

                            ba.balanceUser1 += userPrint4;
                            System.out.println("Операция выполнена успешно.");
                            System.out.println("Новый баланс: " + ba.balanceUser1);

                        } else if (userPrint3.equals(ba.accountNumber2)) {

                            if (userPrint4 <= 0)
                                throw new Exception("Сумма должна быть больше 0.");
                            if (ba.isBlocked2)
                                throw new Exception("Счет заблокирован.");

                            ba.balanceUser2 += userPrint4;
                            System.out.println("Новый баланс: " + ba.balanceUser2);

                        } else {
                            throw new Exception("Неверный номер счета.");
                        }
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Введите номер счета: ");
                    String userPrint5 = scn.nextLine();

                    System.out.print("Введите сумму для списания: ");
                    double userPrint6 = scn.nextDouble();
                    scn.nextLine();

                    try {
                        if (userPrint5.equals(ba.accountNumber1)) {

                            if (ba.isBlocked1)
                                throw new Exception("Счет заблокирован.");
                            if (userPrint6 > ba.balanceUser1)
                                throw new Exception("Недостаточно средств.");

                            ba.balanceUser1 -= userPrint6;
                            System.out.println("Операция выполнена успешно.");
                            System.out.println("Новый баланс: " + ba.balanceUser1);

                        } else if (userPrint5.equals(ba.accountNumber2)) {

                            if (ba.isBlocked2)
                                throw new Exception("Счет заблокирован.");
                            if (userPrint6 > ba.balanceUser2)
                                throw new Exception("Недостаточно средств.");

                            ba.balanceUser2 -= userPrint6;
                            System.out.println("Операция выполнена успешно.");
                            System.out.println("Новый баланс: " + ba.balanceUser2);

                        } else {
                            throw new Exception("Неверный номер счета.");
                        }
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Введите номер счета отправителя: ");
                    String userPrint7 = scn.nextLine();

                    System.out.print("Введите номер счета получателя: ");
                    String userPrint8 = scn.nextLine();

                    System.out.print("Введите сумму перевода: ");
                    double userPrint9 = scn.nextDouble();
                    scn.nextLine();

                    try {
                        if (userPrint7.equals(ba.accountNumber1)
                                && userPrint8.equals(ba.accountNumber2)) {

                            if (ba.isBlocked1)
                                throw new Exception("Счет отправителя заблокирован.");
                            if (userPrint9 > ba.balanceUser1)
                                throw new Exception("Недостаточно средств.");

                            ba.balanceUser1 -= userPrint9;
                            ba.balanceUser2 += userPrint9;

                        } else if (userPrint7.equals(ba.accountNumber2)
                                && userPrint8.equals(ba.accountNumber1)) {

                            if (ba.isBlocked2)
                                throw new Exception("Счет отправителя заблокирован.");
                            if (userPrint9 > ba.balanceUser2)
                                throw new Exception("Недостаточно средств.");

                            ba.balanceUser2 -= userPrint9;
                            ba.balanceUser1 += userPrint9; // ОШИБКА БЫЛА ЗДЕСЬ
                        } else {
                            throw new Exception("Неверные номера счетов.");
                        }

                        System.out.println("Перевод выполнен успешно.");
                        System.out.println("Баланс 001: " + ba.balanceUser1);
                        System.out.println("Баланс 002: " + ba.balanceUser2);

                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Введите номер счета: ");
                    String block = scn.nextLine();

                    if (block.equals(ba.accountNumber1))
                        ba.isBlocked1 = true;
                    else if (block.equals(ba.accountNumber2))
                        ba.isBlocked2 = true;

                    System.out.println("Счет заблокирован.");
                    break;

                case 6:
                    System.out.print("Введите номер счета: ");
                    String unblock = scn.nextLine();

                    if (unblock.equals(ba.accountNumber1))
                        ba.isBlocked1 = false;
                    else if (unblock.equals(ba.accountNumber2))
                        ba.isBlocked2 = false;

                    System.out.println("Счет разблокирован.");
                    break;

                case 0:
                    System.out.println("Спасибо за использование банковской системы!");
                    return;
            }
        }
    }
}
