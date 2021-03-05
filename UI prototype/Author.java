package application;

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

public class Author
{
	private final Stage stage = new Stage();
	
	String a = "a 1"; 
	String b = "b 2"; 
	String c = "c 3";
	String d = "d 4";
	String e = "e 5";
	String f = "f 6";
	
	ListView<Authorr> listView = new ListView<Authorr>();
	ObservableList<Authorr> listData = FXCollections.observableArrayList();
	
	public Author(String name) {
		
		try
		{
			
		listData.add(new Authorr(a));
		listData.add(new Authorr(b));
		listData.add(new Authorr(c));
		listData.add(new Authorr(d));
		listData.add(new Authorr(e));
		listData.add(new Authorr(f));
		listView.setItems(listData);
		
		listView.setCellFactory(new Callback<ListView<Authorr>,ListCell<Authorr>>() {

			@Override
			public ListCell<Authorr> call(ListView<Authorr> param)
			{
				return new MyListCell();
			}				
		});
	
		DropShadow dropshadow = new DropShadow();
        dropshadow.setRadius(10);
        dropshadow.setOffsetX(0);
        dropshadow.setOffsetY(0);
        dropshadow.setSpread(0.1);
        dropshadow.setColor(Color.BLACK);
        
		BorderPane root = new BorderPane();
		root.setCenter(listView);
		root.setEffect(dropshadow);
		
		Scene scene = new Scene(root, 350, 175);
		stage.setScene(scene);
		stage.setTitle("Author List");
		stage.setResizable(false);
		stage.show();

	} catch (Exception e)
	{
		e.printStackTrace();
	}
}
	
	static class MyListCell extends ListCell<Authorr>
	{
		@Override
		public void updateItem(Authorr item, boolean empty)
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
	
	public class Authorr
	{
		public String name;
		
		public Authorr(String name)
		{	
			this.name = name;
		}
		
		
	}


}