package OOLaS_lab44;

import OOLaS_lab44.Catalog.Catalog;
import OOLaS_lab44.Catalog.Disc;
import OOLaS_lab44.Catalog.Song;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Main {

    public static String getLine() {
        System.out.print(">> ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = 0;
        StringBuilder out = new StringBuilder();
        while (true) {
            try {
                c = br.read();
                if (c == -1 || c == '\n') break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.append((char) c);
        }
        return out.toString();
    }

    public static String getInput() {
        return getInput("");
    }

    public static String getInput(String message) {
        if (message != null && !message.equals("")) System.out.println(message);
        return getLine();
    }

    public static int getMenuChoice(int bound) {
        int out = -1;
        while (true) {
            try {
                out = Integer.parseInt(getLine().trim());
                if (out < 1 || out > bound) {
                    System.out.println("Такого пункта меню нет, может другой выберем?");
                } else break;
            } catch (NumberFormatException exception) {
                System.out.println("Вы что-то не то ввели, давайте попробуем еще раз.");
            }
        }
        return out;
    }

    public static boolean getConfirm() {
        System.out.println("Вы уверены (y/n) ?");
        Boolean ans = null;
        while (ans == null) {
            String input = getLine().toLowerCase(Locale.ROOT);
            switch (input) {
                case "y", "yes" -> ans = true;
                case "n", "no" -> ans = false;
                default -> System.out.println("Вы что-то не то ввели, давайте попробуем еще раз.");
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("Map");
        System.out.println("""
                Реализовать простейший каталог музыкальных компакт-дисков, который позволяет:
                •\tДобавлять и удалять диски.
                •\tДобавлять и удалять песни.
                •\tПросматривать содержимое целого каталога и каждого диска в отдельности.
                Осуществлять поиск всех записей заданного исполнителя по всему каталогу
                """);

        /*
         * Главное меню:
         *   Просмотр всего каталога
         *   Выбор диска для просмотра (переход в другой
         *   Добавление нового диска
         *   Удаление диска
         *   Загрузить каталог из файла (передаем имя файла)
         *   Сохранить каталог в файл (передаем имя файла)
         *   Поиск по исполнителю (передаем строку поиска)
         *
         * Меню диска:
         *   Просмотр всего диска
         *   Добавление песни
         *   Удаление песни
         *
         */


        Song a = new Song("Song 1", "Album 1", "Artist 1");
        Song b = new Song("Song 2", "Album 1", "Artist 1");
        Song c = new Song("Song 3", "Album 1", "Artist 1");
        Song d = new Song("Song 4", "Album 2", "Artist 1");
        Song e = new Song("Song 5", "Album 2", "Artist 2");

        Disc d1 = new Disc("Disc 1");
        d1.addSong(a);
        d1.addSong(b);
        d1.addSong(c);

        Disc d2 = new Disc("Disc 2");
        d2.addSong(d);
        d2.addSong(e);

        Catalog cat = new Catalog("My disc shelf");
        cat.addDisc(d1);
        cat.addDisc(d2);

        String path = "catalog.dat";

        cat.SaveTo(path);

        cat = new Catalog();
        cat.ReadFrom(path);


        String[] mainMenuTitles = new String[]{
                "Просмотреть весь каталог",
                "Выбрать диск",
                "Добавить диск",
                "Удалить диск",
                "Поиск по исполнителю",
                "Загрузить каталог из файла",
                "Сохранить каталог в файл",
                "Выход"
        };

        String[] discMenuTitles = new String[]{
                "Просмотреть диск",
                "Добавить песню",
                "Удалить песню",
                "Назад"
        };

        while (true) {
            for (int i = 0; i < mainMenuTitles.length; ++i) {
                System.out.println((i + 1) + ". " + mainMenuTitles[i]);
            }

            boolean out = false;
            switch (getMenuChoice(mainMenuTitles.length)) {
                case 1 -> {
                    //просмотреть каталог
                    System.out.println(cat);
                }
                case 2 -> {
                    //выбрать диск
                    Disc disc = cat.getDisc(getInput("Введите название диска:"));
                    if (disc == null) System.out.println("Диск не найден");
                    else {
                        System.out.println("Выбранный диск: " + disc.getName());
                        while (true) {
                            boolean out2 = false;
                            for (int i = 0; i < discMenuTitles.length; ++i) {
                                System.out.println((i + 1) + ". " + discMenuTitles[i]);
                            }
                            switch (getMenuChoice(discMenuTitles.length)) {
                                case 1 -> {
                                    //просмотреть диск
                                    System.out.println(disc);
                                }
                                case 2 -> {
                                    //добавить песню
                                    Song song = new Song(
                                            getInput("Введите название песни:"),
                                            getInput("Введите название альбома:"),
                                            getInput("Введите название исполнителя:")
                                    );
                                    System.out.println("Хотите добавить песню в определенное место:");
                                    if (getConfirm())
                                        disc.addSong(
                                                song,
                                                Integer.parseInt(getInput("Введите позицию:")) - 1);
                                    else disc.addSong(song);
                                }
                                case 3 -> {
                                    //удалить песню
                                    Song song =
                                            disc.deleteAt(Integer.parseInt(getInput("Введите позицию песни для удаления:")) - 1);
                                    if (song == null) System.out.println("Песня не найдена");
                                    else System.out.println("Успешно удалено");
                                }
                                case 4 -> {
                                    //выход
                                    out2 = getConfirm();
                                }
                            }
                            if (out2) break;
                        }
                    }
                }
                case 3 -> {
                    //добавить диск
                    if (!cat.addDisc(new Disc(getInput("Введите название диска:"))))
                        System.out.println("Такой диск уже есть в этом каталоге");
                }
                case 4 -> {
                    //удалить диск
                    Disc disc = cat.deleteDisc(getInput("Какой диск вы хотите удалить?"));
                    if (disc == null) System.out.println("Диск не найден");
                    else System.out.println("Успешно удалено");
                }
                case 5 -> {
                    //поиск по исполнителю
                    Disc srch = new Disc("Результаты поиска:",
                            cat.searchByArtist(getInput("Введите исполнителя:")));
                    System.out.println(srch);

                }
                case 6 -> {
                    //загрузить каталог из файла
                    cat.ReadFrom(getInput("Введите название файла:"));
                }
                case 7 -> {
                    //сохранить каталог в файл
                    cat.SaveTo(getInput("Введите название файла:"));
                }
                case 8 -> {
                    //выход
                    out = getConfirm();
                }
            }

            if (out) {
                System.out.println("Всего хорошего!");
                break;
            }

        }


    }
}
