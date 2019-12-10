package OrganizerApp2;

import static OrganizerApp2.Main.GetDBHandler;

public class OrgEvent
{
    public int ID;
    public String name;
    public String date;
    public String description;
    public int typeID;
    public OrgEvent(int ID, String name, String date, String description, int typeID)
    {
        this.ID = ID;
        this.name = name;
        this.date = date;
        this.description = description;
        this.typeID = typeID;
    }
    @Override
    public String toString()
    {
        String genre = GetDBHandler().findTypeByID(this.typeID);
        return String.format("ID: %s | Название: %s | Дата: %s | Описание: %s | Тип: %s",
                this.ID, this.name, this.date, this.description, genre != null ? genre : "не установлен");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }
}
