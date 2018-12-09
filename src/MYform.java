import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.lang.String;
import java.awt.event.*;

public class MYform extends JFrame
{
    private static final long serialVersionUID = 1L;

    private  JButton      button1    = null;
    private  JFileChooser fileChooser;
    DefaultMutableTreeNode root = new DefaultMutableTreeNode();
    DefaultTreeModel model = new DefaultTreeModel(root);
    private DefaultMutableTreeNode roots = new DefaultMutableTreeNode("", true);
    private JTree tree1 = new JTree(roots);
    private File selectedFile;

    public MYform() {
        super("Каталог файлов по выбранному пути");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Кнопка создания диалогового окна для выбора директории
        // Создание экземпляра JFileChooser
        fileChooser = new JFileChooser();
        // Подключение слушателей к кнопкам
        addFileChooserListeners();
        // Размещение кнопок в интерфейсе
        JPanel contents = new JPanel();
        contents.add(button1   );
        contents.add(tree1   );
        setContentPane(contents);
        // Вывод окна на экран
        setSize(500, 500);
        setVisible(true);
    }
    int result;

    private void addFileChooserListeners()
    {
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выбор директории");
                // Определение режима - только каталог
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.isDirectory();
                    }

                    @Override
                    public String getDescription() {
                        return "";
                    }
                });
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                result = fileChooser.showOpenDialog(MYform.this);
                // Если директория выбрана, покажем ее в сообщении
                selectedFile = fileChooser.getSelectedFile();
                if (result == JFileChooser.APPROVE_OPTION )
                    root = new DefaultMutableTreeNode(selectedFile.getName());
                String[] directories = selectedFile.list();
                for (String d : directories) {
                    root.add(new DefaultMutableTreeNode(d));
                }
                model.setRoot(root);
            }
        });
        add(button1, BorderLayout.SOUTH);
        tree1.setModel(model);
        JScrollPane scrollPane = new JScrollPane(tree1);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);


    }


    public static void main(String[] args)
    {
        // Локализация компонентов окна JFileChooser
        UIManager.put("FileChooser.openButtonText"      , "Открыть"               );
        UIManager.put("FileChooser.cancelButtonText"    , "Отмена"                );
        UIManager.put("FileChooser.fileNameLabelText"   , "Наименование файла"    );
        UIManager.put("FileChooser.filesOfTypeLabelText", "Типы файлов"           );
        UIManager.put("FileChooser.lookInLabelText"     , "Директория"            );
        UIManager.put("FileChooser.folderNameLabelText" , "Путь директории"       );

        new MYform();

    }
}
