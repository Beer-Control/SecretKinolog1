package SecretViewer;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class DbHandler {

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:/home/user/DataBases/SecretViewer.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private DbHandler() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

//    public List<Game> getAllGames() {
//
//        // Statement используется для того, чтобы выполнить sql-запрос
////        try (Statement statement = this.connection.createStatement()) {
////            // В данный список будем загружать наши продукты, полученные из БД
////            List<Game> games = new ArrayList<Game>();
////            // В resultSet будет храниться результат нашего запроса,
////            // который выполняется командой statement.executeQuery()
////            ResultSet resultSet = statement.executeQuery("SELECT gameID, players, films,  FROM games");
////            // Проходимся по нашему resultSet и заносим данные в products
////            while (resultSet.next()) {
////                games.add(new Game(resultSet.getInt("gameID"),
////                        resultSet.("good"),
////                        resultSet.getDouble("price"),
////                        resultSet.getString("category_name")));
////            }
////            // Возвращаем наш список
////            return products;
////
////        } catch (SQLException e) {
////            e.printStackTrace();
////            // Если произошла ошибка - возвращаем пустую коллекцию
////            return Collections.emptyList();
//    }
//
//}

    // Добавление продукта в БД
    public void addGame(Game game) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Products(`gameID`, `players`, `films`, ,`views`, `startOfGame` `endOfGame`) " +
                        "VALUES(?, ?, ?, ?, ?)")) {
            statement.setObject(1, game.gameID);
            statement.setObject(2, game.players);
            statement.setObject(3, game.films);
//            statement.setObject(5, game.startOfGame);
//            statement.setObject(5, game.endOfGame);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление продукта по id
    public void deleteGame(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Products WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
