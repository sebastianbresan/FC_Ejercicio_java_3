package FC;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;


public class Main {

    public static void main(String[] args) {

        //---------- DECLARACION E INICIALIZACION DE VARIABLES

        Argon2 argon2 = new Argon2();

        ArrayList<User> users = new ArrayList<>();

        byte option;

        int contador = 1;

        boolean cicle = true;

        StringBuilder sb = new StringBuilder();

        while (cicle)

            try {

                //----------MENU DE OPCIONES

                option = Byte.parseByte(JOptionPane.showInputDialog(
                        """
                                *** FC EJERCICIO 2 ***
                                1. REGISTER USER
                                2. LOGIN USER
                                3. LIST USERS
                                0. EXIT
                                CHOOSE A OPTION :)"""));

                switch (option) {
                    case 1 -> {

                        //----------PROCESO DE REGISTRO DE USUARIO
                        //----------PRIMERO SE PIDEN LOS DATOS
                        //----------SE COMPRUEBAN LOS FORMATOS DE LOS CAMPOS
                        //----------LUEGO SE HASHEA LA CONTRASEÑA
                        //----------SE REALIZA UN APPEND AL STRINGBUILDER CON EL USUARIO ALMACENADO
                        //----------SE ALMACENA EL USUARIO EN EL ARRAY DE LA CLASE MAIN
                        //----------Y POR ULTIMO SE ACTUALIZA EL ARRAY DE LA CLASE USER

                        User user = new User();
                        user.setEmail(JOptionPane.showInputDialog("Type a email").toLowerCase(Locale.ROOT));
                        while (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
                            user.setEmail(JOptionPane.showInputDialog("Email format is not valid, retry").toLowerCase(Locale.ROOT));
                        }
                        String password = JOptionPane.showInputDialog("Type a password");
                        while (password.length() < 6) {
                            password = (JOptionPane.showInputDialog("Minimum 6 characters"));
                        }
                        String hashedPassword = argon2.hashPassword(password);
                        user.setPassword(hashedPassword);
                        user.setUsers(users);

                        if (user.register(user.getEmail(), user.getPassword())) {
                            JOptionPane.showMessageDialog(null, "Email has already registered");
                        } else {
                            users.add(user);
                            user.setUsers(users);
                            JOptionPane.showMessageDialog(null, "User has been saved correctly\nUser: " + user.getEmail() + ", Password: " + user.getPassword());
                            sb.append("User ").append(contador).append(": ").append(user.getEmail()).append("\n");
                            contador++;
                        }
                    }
                    case 2 -> {

                        //----------PROCESO DE LOGIN DE USUARIO
                        //----------SE COMPRUBA EL FORMATO DE LOS CAMPOS
                        //----------SE MUESTRA EL MENSAJE CORRESPONDIENTE SEGUN LA RESPUESTA DEL METODO LOGIN
                        //---------- 1- CORRECTO / -1 EMAIL NO REGISTRADO / -2 CONTRASEÑA INCORRECTA

                        User user = new User();
                        user.setEmail(JOptionPane.showInputDialog("Type a email").toLowerCase(Locale.ROOT));
                        while (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
                            user.setEmail(JOptionPane.showInputDialog("Email format is not valid, retry").toLowerCase(Locale.ROOT));
                        }
                        String password = JOptionPane.showInputDialog("Type a password");
                        while (password.length() < 6) {
                            password = (JOptionPane.showInputDialog("Minimum 6 characters"));
                        }
                        user.setPassword(password);
                        user.setUsers(users);

                        switch (user.login(user.getEmail(), user.getPassword())) {

                            case 1 -> JOptionPane.showMessageDialog(null, "Success");
                            case -1 -> JOptionPane.showMessageDialog(null, "Email not found in database");
                            case -2 -> JOptionPane.showMessageDialog(null, "Password wrong");
                        }
                    }
                    case 3 -> //----------SE IMPRIMEN EN PANTALLA CADA UNO DE LOS USUARIOS QUE SE ALMACENARON
                            JOptionPane.showMessageDialog(null, sb);

                    case 0 -> {

                        //---------- SE CIERRA EL PROGRAMA

                        JOptionPane.showMessageDialog(null, "Closing ....");
                        cicle = false;
                        System.exit(0);
                    }
                    default -> JOptionPane.showMessageDialog(null, "Choose a valid option");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Choose a valid option");
            }
    }

}

