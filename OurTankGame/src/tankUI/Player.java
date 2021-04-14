package tankUI;

import client.TankClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Player
{
	private final Stage stage = new Stage();
	String name1 = TankClient.getName();
	
	ListView<Playerr> listView = new ListView<Playerr>();
	ObservableList<Playerr> listData = FXCollections.observableArrayList();
	
	HBox hbox =new HBox();
	TextField textField = new TextField();
	Button btnChange = new Button("Add");
	Button btnRemove = new Button("Delete");
	
	
	
	public Player(String name) {
		
		try
		{
			
		listData.add(new Playerr("Player: " + name1));	
		listView.setItems(listData);
		
		listView.setCellFactory(new Callback<ListView<Playerr>,ListCell<Playerr>>() {

			@Override
			public ListCell<Playerr> call(ListView<Playerr> param)
			{
				return new MyListCell();
			}				
		});
		

		hbox.getChildren().addAll(textField, btnChange,btnRemove);
		HBox.setHgrow(textField, Priority.ALWAYS);
		
		BorderPane root = new BorderPane();
		root.setCenter(listView);
		root.setTop(hbox);
		
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
		
		btnChange.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0)
			{
				onAdd();
			}
        });
		
		btnRemove.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event)
			{
				onRemove();
			}				
		});
		
	} catch (Exception e)
	{
		e.printStackTrace();
	}
}
	
	public void onAdd()
	{
		String name = textField.getText();
		listData.add(new Playerr("Player: " + name));
	}
	
	public void onRemove()
	{
		int index = listView.getSelectionModel().getSelectedIndex();
		if(index >=0 )
		{
			listData.remove( index );
		}		
	}
	
	static class MyListCell extends ListCell<Playerr>
	{
		@Override
		public void updateItem(Playerr item, boolean empty)
		{
			super.updateItem(item, empty);
			
			if (item == null)
			{
				this.setText(""); 
			}
			else
			{
				this.setText( item.name  ); 
			}
		}
	}
	
	public class Playerr
	{
		public String name;
		
		public Playerr(String name)
		{	
			this.name = name;
		}
		
		
	}


}

