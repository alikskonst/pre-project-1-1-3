package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        new Util().init();
        UserService userService = new UserServiceImpl();

        // пытаемся удалить таблицу которой нет (проверка if exists)
        userService.dropUsersTable();

        // пытаемся создать таблицу
        userService.createUsersTable();

        // пытаемся создать таблицу (проверка if exists)
        userService.createUsersTable();

        // пытаемся создать пользователей
        System.out.println("---");
        userService.saveUser("Tom", "Kotovich", (byte) 5);
        userService.saveUser("Jerry", "Myshevich", (byte) 1);
        userService.saveUser("Cheep", "Burundukovich", (byte) 1);
        userService.saveUser("Dail", "Burundukovich", (byte) 1);

        // получаем всех пользователей
        System.out.println("---");
        List<User> userList = userService.getAllUsers();
        print(userList);

        // пытаемся удалить пользователя
        System.out.println("---");
        User user = userList.get(0);
        userService.removeUserById(user.getId());

        // проверяем
        System.out.println("---");
        userList = userService.getAllUsers();
        print(userList);

        // удаляем всех
        userService.cleanUsersTable();

        // проверяем
        System.out.println("---");
        userList = userService.getAllUsers();
        print(userList);

        // удаляем таблицу
        userService.dropUsersTable();
    }

    private static void print(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }
}
