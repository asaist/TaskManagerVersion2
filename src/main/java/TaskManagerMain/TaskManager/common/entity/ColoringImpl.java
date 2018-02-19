package TaskManagerMain.TaskManager.common.entity;


public class ColoringImpl implements Coloring, Entity {
    private String color;
    private int id;

    public String getColor() {
        return color;
    }

    ColoringImpl(String color) {
        this.color = color;
    }

    @Override
    public String create(String S) {
        return S;
    }

    @Override
    public String update(String S) {
        return S;
    }

    @Override
    public String delete(String S) {
        return S;
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof ColoringImpl) {
            ColoringImpl coloring = (ColoringImpl) anObject;
                /*if (name == null || assignee.getName() == null)  {
                    return false;
                }*/

            if (!color.equals(coloring.getColor())) {
                return false;
            }


        }
        return true;
    }

    @Override
    public String toString() {
        return (color);
    }

    @Override
    public Integer getId() {
        return id;
    }

}
