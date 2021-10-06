package com.poo2109;

// Integrantes : Igor Tamagnoni de Freitas e Kaua Victor

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Gui extends JFrame {

    private ArrayList<Disciplina> disciplinaArrayList = new ArrayList<>();
    private final JPanel panel;
    private JButton inserir, alterar, consultar, excluir;
    private JTextField textField1, textField2, textField3;
    private JLabel label1, label2, label3;
    private JList<Disciplina> list;
    private final DefaultListModel<Disciplina> dlm = new DefaultListModel<>();

    public Gui() {
        super("Trabalho");

        ImageIcon imageIcon = new ImageIcon("ifescol.png");
        setIconImage(imageIcon.getImage());

        panel = new JPanel(new FlowLayout());
        panel.setForeground(new Color(-12961222));
        panel.setBackground(new Color(-12961222));

        list = new JList<>(dlm);

        inserir = new JButton("Inserir");
        inserir.setFocusable(false);
        alterar = new JButton("Alterar");
        alterar.setFocusable(false);
        consultar = new JButton("Consultar");
        consultar.setFocusable(false);
        excluir = new JButton("Excluir");
        excluir.setFocusable(false);

        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(80, 30));
        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(80, 30));
        textField3 = new JTextField();
        textField3.setPreferredSize(new Dimension(80, 30));

        label1 = new JLabel("ID:");
        label1.setForeground(new Color(-5029922));
        label2 = new JLabel("NOME:");
        label2.setForeground(new Color(-5029922));
        label3 = new JLabel("CH:");
        label3.setForeground(new Color(-5029922));

        inserir.addActionListener(e -> inserir());
        alterar.addActionListener(e -> alterar());
        consultar.addActionListener(e -> consultar());
        excluir.addActionListener(e -> excluir());

        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(label3);
        panel.add(textField3);
        panel.add(inserir);
        panel.add(alterar);
        panel.add(consultar);
        panel.add(excluir);
        panel.add(list);

        this.setContentPane(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void inserir() {
        Pattern p1 = Pattern.compile("[0-9]+$");
        Pattern p2 = Pattern.compile("^[a-zA-Z ]+$");

        if (textField1.getText().matches(p1.pattern()) && textField2.getText().matches(p2.pattern()) && textField3.getText().matches(p1.pattern())) {
            dlm.addElement(new Disciplina(Integer.parseInt(textField1.getText()), textField2.getText(), Integer.parseInt(textField3.getText())));
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Formato inválido");
        }
    }

    public void alterar() {
        list.getSelectedValue().setId(Integer.parseInt(textField1.getText()));
        list.getSelectedValue().setNome(textField2.getText());
        list.getSelectedValue().setCargaHoraria(Integer.parseInt(textField3.getText()));
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
    }

    public void consultar() {
        JOptionPane.showMessageDialog(null,
                "ID: " + list.getSelectedValue().getId() + "\n" +
                        "Nome: "+ list.getSelectedValue().getNome() + "\n" +
                        "Carga Horária: " + list.getSelectedValue().getCargaHoraria()
                ,"Consulta Dados",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void excluir() {
        if (dlm.size() > 0) dlm.remove(list.getSelectedIndex());
    }

    public static void main(String[] args) {
        new Gui();
    }
}