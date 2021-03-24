package tankUI;


import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
// boolean ���жϳ��̣�true - ��long �� false - ��short
public class Auto_window{
	public Auto_window(long time, String msg, String title, boolean BL){
		Stage s1 = new Stage();
		s1.getIcons().add(new Image("images/icon_tank.jpg"));
		s1.setAlwaysOnTop(true);
		s1.initModality(Modality.APPLICATION_MODAL);//��ֹ�¼����ݵ��κ�����Ӧ�ó��򴰿�
		Button closeBtn = new Button("copy that");
		closeBtn.setOnAction(e -> {
			s1.close();
		}); 
		
		VBox root = new VBox();
		root.setPadding(new Insets(20));
		root.setAlignment(Pos.BASELINE_CENTER);
		root.setSpacing(20);
		Label l1 = new Label(msg);
		if(BL == true) {
			l1.setPrefSize(1000, 100);
		}
		else {
			l1.setPrefSize(500, 50);
		}
		l1.getStyleClass().add("font");
//		l1.setScaleX(2);
		l1.setScaleY(2);
		
		root.getChildren().addAll(l1, closeBtn);
		Scene scene = new Scene(root);
		s1.setScene(scene);
		s1.setTitle("Auto-close window: "+title);
		s1.show();
 
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(time);
				if (s1.isShowing()) {
					Platform.runLater(() -> s1.close());
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		});
		thread.setDaemon(true);
		thread.start();
	}
	
}
	