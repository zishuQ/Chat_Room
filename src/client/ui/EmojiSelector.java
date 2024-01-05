package client.ui;

import com.vdurmont.emoji.EmojiParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import static client.ui.ChatFrame.sendArea;

public class EmojiSelector extends JDialog {
    private static final int EMOJIS_PER_ROW = 5;
    private static final int MAX_ROWS = 4; // 最大行数
    private static final int EMOJI_SIZE = 32;
    private static final int SPACING = 5;

    public static List<String> emojis;

    public EmojiSelector(Frame parent) {
        super(parent, "选择Emoji", true);

        JButton emojiButton = new JButton("选择Emoji");

        // 定义要显示的20个表情
        emojis = Arrays.asList(
                ":joy:", ":heart_eyes:", ":blush:", ":kissing_heart:",
                ":sob:", ":sweat_smile:", ":sunglasses:", ":unamused:",
                ":grin:", ":rage:", ":scream:", ":expressionless:", ":+1:",
                ":sleeping:", ":cry:", ":eye_roll:", ":triumph:",
                ":innocent:", ":smiling_imp:", ":face_with_cowboy_hat:"
        );

        JPanel emojiPanel = new JPanel();
        emojiPanel.setLayout(new GridLayout(MAX_ROWS, EMOJIS_PER_ROW, SPACING, SPACING)); // 使用GridLayout
        emojiPanel.setBackground(Color.WHITE);

        for (String emoji_base : emojis) {
            String emoji = EmojiParser.parseToUnicode(emoji_base);
            JButton emojiBtn = new JButton(emoji);
            emojiBtn.setPreferredSize(new Dimension(EMOJI_SIZE, EMOJI_SIZE));
            emojiBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sendArea.append(emoji);
                    dispose();
                }
            });
            emojiPanel.add(emojiBtn);
        }

        JScrollPane scrollPane = new JScrollPane(emojiPanel);
        scrollPane.setPreferredSize(new Dimension(EMOJIS_PER_ROW * (EMOJI_SIZE + SPACING), MAX_ROWS * (EMOJI_SIZE + SPACING)));
        setSize(new Dimension(EMOJIS_PER_ROW * (EMOJI_SIZE + SPACING), MAX_ROWS * (EMOJI_SIZE + SPACING)));
        setMinimumSize(new Dimension(EMOJIS_PER_ROW * (EMOJI_SIZE + SPACING), MAX_ROWS * (EMOJI_SIZE + SPACING)));


        getContentPane().add(scrollPane, BorderLayout.CENTER);

        emojiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    Point location = emojiButton.getLocationOnScreen();
                    setLocation(location.x - 20, location.y - getHeight());
                    setVisible(true);
                });
            }
        });

        pack();
        setLocationRelativeTo(parent);
    }
}
