package ra.run;

import ra.businessImp.Book;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    private static List<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("************ Quản lý sách ************");
            System.out.println("1. Nhập số sách và nhập thông tin sách");
            System.out.println("2. Hiển thị thông tin các sách");
            System.out.println("3. Sắp xếp sách theo lợi nhuận giảm dần");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm sách theo tên sách");
            System.out.println("6. Thay đổi trạng thái của sách theo mã sách");
            System.out.println("7. Thoát");
            System.out.println("Chọn chức năng (1-7): ");
            choice = Integer.parseInt(scanner.nextLine());// nuốt dòng
            System.out.println();

            switch (choice) {
                case 1:
                    inputBooksInfo(scanner);
                    System.out.println();
                    break;
                case 2:
                    displayBooks();
                    System.out.println();
                    break;
                case 3:
                    sortBooksByProfit();
                    System.out.println();
                    break;
                case 4:
                    deleteBookById(scanner);
                    System.out.println();
                    break;
                case 5:
                    searchBookByName(scanner);
                    System.out.println();
                    break;
                case 6:
                    changeBookStatusById(scanner);
                    System.out.println();
                    break;
                case 7:
                    System.out.println("Đã thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    System.out.println();
                    break;
            }
        } while (choice != 7);

        scanner.close();
    }

    public static void inputBooksInfo(Scanner scanner) {
        System.out.println("Nhập số lượng sách cần thêm");
        int numberOfBooks = scanner.nextInt();
        scanner.nextLine();

        System.out.println("* Nhập thông tin cho sách *");
        for (int i = 0; i < numberOfBooks; i++) {
            System.out.println("Nhập thông tin cho sách thứ "+(i+1));
            Book book = new Book();
            book.inputData(scanner);
            bookList.add(book);
            System.out.println();
        }

    }

    public static void displayBooks() {
        System.out.println("* Hiển thị thông tin các sách *");
        for (Book book : bookList) {
            book.displayData();
            System.out.println();
        }
    }

    public static void sortBooksByProfit() {
        boolean check = true;
        for (int i = 0; i < bookList.size()-1 && check; i++) {
            check = false;
            for (int j = 0; j < bookList.size()-1-i; j++) {
                if (bookList.get(j).compareTo(bookList.get(j+1)) < 0) {
                    Book temp = bookList.get(j);
                    bookList.set(j, bookList.get(j+1));
                    bookList.set(j+1, temp);
                    check = true;
                }
            }
        }
        System.out.println("Sách đã được sắp xếp theo lợi nhuận giảm dần.");
    }

    public static void deleteBookById(Scanner scanner) {
        System.out.println("Nhập mã sách cần xóa: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        Iterator<Book> iterator = bookList.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBookId() == bookId) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Đã xóa sách có mã sách " + bookId);
        } else {
            System.out.println("Không tìm thấy sách có mã sách " + bookId);
        }
    }

    public static void searchBookByName(Scanner scanner) {
        if (bookList.isEmpty()) {
            System.out.println("Không có sách trong kho");
        } else {
            System.out.println("Nhập tên sách cần tìm kiếm: ");
            String bookName = scanner.nextLine();
            boolean found = false;
            System.out.println("\n Kết quả tìm kiếm");
            for (Book book :
                    bookList) {
                if (book.getBookName().contains(bookName)) {
                    book.displayData();
                    System.out.println("-------------------------------------");
                    found = true;

                }
            }
            if (!found) {
                System.out.println("Không tìm thấy tên sách");
            }
        }
    }

    public static void changeBookStatusById(Scanner scanner) {
        if (bookList.isEmpty()) {
            System.out.println("Không có sách trong kho");
        } else {
            System.out.println("Nhập mã sách cần thay đổi trạng thái: ");
            int bookId = Integer.parseInt(scanner.nextLine());

            boolean found = false;

            for (Book book : bookList) {
                if (book.getBookId() == bookId) {
                    boolean currentStatus = book.isBookStatus();
                    book.setBookStatus(!currentStatus);
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("Đã thay đổi trạng thái của sách có mã sách " + bookId);
            } else {
                System.out.println("Không tìm thấy sách có mã sách " + bookId);
            }
        }

    }
}

