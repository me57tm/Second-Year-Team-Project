package tankUI;

import java.util.List;

import client.TankClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import physics.Tank;

/**
 * The list in the network mode can view the number of players, ID and name
 */
public class Player {
	private final Stage stage = new Stage();
	String name1 = TankClient.getName();

	ListView<Playerr> listView = new ListView<Playerr>();
	ObservableList<Playerr> listData = FXCollections.observableArrayList();

	public Player(List<Tank> tanks) {

		try {
            int i = 0;
			for (Tank t : tanks) {
				i++;
		    listData.add(new Playerr("Player" + i + ": " + t.getName()));
		    listData.add(new Playerr("ID: " + t.getId()));		    
				}
				
			
			listView.setItems(listData);

			listView.setCellFactory(new Callback<ListView<Playerr>, ListCell<Playerr>>() {

				@Override
				public ListCell<Playerr> call(ListView<Playerr> param) {
					return new MyListCell();
				}
			});

			BorderPane root = new BorderPane();
			root.setCenter(listView);

			DropShadow dropshadow = new DropShadow();
			dropshadow.setRadius(10);
			dropshadow.setOffsetX(0);
			dropshadow.setOffsetY(0);
			dropshadow.setSpread(0.1);
			dropshadow.setColor(Color.BLACK);

			Scene scene = new Scene(root, 350, 300);
			stage.setScene(scene);
			stage.setTitle("Player List");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void onAdd()
//	{
//
//		listData.add(new Playerr("Player: " + ));
//	}

	public void onRemove() {
		int index = listView.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			listData.remove(index);
		}
	}

	static class MyListCell extends ListCell<Playerr> {
		@Override
		public void updateItem(Playerr item, boolean empty) {
			super.updateItem(item, empty);

			if (item == null) {
				this.setText("");
			} else {
				this.setText(item.name);
			}
		}
	}

	public class Playerr {
		public String name;


		public Playerr(String name) {
			this.name = name;
		}

	}

}
