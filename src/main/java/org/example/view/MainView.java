package org.example.view;

import org.example.controller.LabelController;
import org.example.controller.PostController;
import org.example.controller.WriterController;
import org.example.model.Label;
import org.example.repository.LabelRepository;
import org.example.repository.PostRepository;
import org.example.repository.WriterRepository;
import org.example.repository.gson.GsonLabelRepository;
import org.example.repository.gson.GsonPostRepository;
import org.example.repository.gson.GsonWriterRepositoryI;
import org.example.util.ConsoleUtil;

import java.io.IOException;
import java.util.Scanner;

public class MainView {
    private final WriterController writerController;
    private final PostController postController;
    private final LabelController labelController;

    public MainView() {
        WriterRepository writerRepository = new GsonWriterRepositoryI();
        PostRepository postRepository = new GsonPostRepository() {
            @Override
            protected Label readLabelsFromFile() {
                return null;
            }
        };
        LabelRepository labelRepository = new GsonLabelRepository();
        this.writerController = new WriterController(writerRepository, postRepository);
        this.postController = new PostController(postRepository, labelRepository);
        this.labelController = new LabelController(labelRepository);

    }

    public void displayMenu() {
        try (Scanner sc = new Scanner(System.in)) {
            WriterView writerView = new WriterView(sc, writerController);
            PostView postView = new PostView(sc, postController);
            LabelView labelView = new LabelView(sc, labelController);
            ConsoleUtil.writeEmptyLiens();
            while (true) {
                System.out.println("--- Writer keeper menu ---");
                System.out.println("1. Writers menu");
                System.out.println("2. Posts menu");
                System.out.println("3. Labels menu");
                System.out.println("6. Exit");
                int choice = ConsoleUtil.readInt(sc, "Choose an option: ");
                ConsoleUtil.writeEmptyLines();
                switch (choice) {
                    case 1 -> writerView.displayMenu();
                    case 2 -> postView.displayMenu();
                    case 3 -> labelView.displayMenu();
                    case 6 -> {
                        System.out.println("Exiting application...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException ex) {
            System.out.println("IO Exception. Try again.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
