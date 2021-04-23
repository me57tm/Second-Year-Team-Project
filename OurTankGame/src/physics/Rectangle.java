package physics;

public class Rectangle
{
    // (x,y) represents the top-Left corner of the Rectangle
    double x;
    double y;
    double width;
    double height;
//    //added for collision
//    public Vector position;
//    public double rotation;

    public Rectangle()
    {
        this.setPosition(0,0);
        this.setSize(1,1);
//        this.position = new Vector();
//        this.rotation = 0;
    }

    public Rectangle(double x, double y, double w, double h)
    {
        this.setPosition(x,y);
        this.setSize(w,h);
    }
    
    public double getWidth() {
    	return this.width;
    }
    
    public double getHeight() {
    	return this.height;
    }

    public void setPosition(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void setByCoords(double x1, double y1, double x2, double y2) {
    	this.setPosition(x1,y1);
    	this.setSize(x2-x1,y2-y1);
    }

    public void setSize(double w, double h)
    {
        this.width = w;
        this.height = h;
    }
    
    public void rotate(double angle) {
    	
    }

    public boolean overlaps(Rectangle other)
    {
        // Four cases where there is no overlap
        // 1 : this is to the Left of other
        // 2 : this is to the Right of the other
        // 3 : this is above other
        // 4 : other is above this
        boolean noOverlap =
                this.x + this.width < other.x ||
                        other.x + other.width < this.x ||
                        this.y + this.height < other.y ||
                        other.y + other.height < this.y;
        return !noOverlap;
    }
    
//    x_overlaps = (a.left < b.right) && (a.right > b.left)
//    		y_overlaps = (a.top < b.bottom) && (a.bottom > b.top)
//    		collision = x_overlaps && y_overlaps


}
