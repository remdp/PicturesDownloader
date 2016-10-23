
public class Main {

    public static void main(String[] args) {

        String[] pathes = new String[5];
        pathes[0] = "http://minionomaniya.ru/wp-content/uploads/2016/01/%D0%9A%D0%B5%D0%B2%D0%B8%D0%BD.jpg";
        pathes[1] = "http://minionomaniya.ru/wp-content/uploads/2016/03/Minions-foto-iz-filma.jpg";
        pathes[2] = "http://minionomaniya.ru/wp-content/uploads/2015/10/миньоны-мультфильм-картинки.jpg";
        pathes[3] = "http://minionomaniya.ru/wp-content/uploads/2015/10/картинки-из-мультфильма-миньоны-2015.jpg";
        pathes[4] = "http://minionomaniya.ru/wp-content/uploads/2015/08/Миньон-Боб.jpg";

        new ImagesLoader(pathes).loadImages();
    }
}