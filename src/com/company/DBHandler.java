package com.company;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBHandler
{
    private static DBHandler instance = null;
    public static synchronized DBHandler getInstance() throws SQLException
    {
        if(instance == null) instance = new DBHandler();
        return instance;
    }
    private Connection connection;
    private DBHandler() throws SQLException
    {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir").replace("\\", "/") + "/organizerDB.sqlite");
    }
    public List<OrgEvent> getAllEvents()
    {
        try (Statement statement = this.connection.createStatement())
        {
            List<OrgEvent> orgEvents = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT Event_ID, Name, Date, Description, Type_ID FROM Event");
            while(resultSet.next())
            {
                orgEvents.add(new OrgEvent(resultSet.getInt("Event_ID"), resultSet.getString("Name"), resultSet.getString("Date"), resultSet.getString("Description"),
                        resultSet.getInt("Type_ID")));
            }
            return orgEvents;
        }
        catch(SQLException e)
        {
            return Collections.emptyList();
        }
    }
    public List<EventType> getAllEventTypes()
    {
        try(Statement statement = this.connection.createStatement())
        {
            List<EventType> eventTypes = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT Type_ID, Name, Description FROM Types");
            while(resultSet.next())
            {
                eventTypes.add(new EventType(resultSet.getInt("Type_ID"), resultSet.getString("Name"), resultSet.getString("Description")));
            }
            return eventTypes;
        }
        catch(SQLException e)
        {
            return Collections.emptyList();
        }
    }
    public List<OrgEvent> findEventByDate(String date)
    {
        try(Statement statement = this.connection.createStatement())
        {
            List<OrgEvent> orgEvents = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT Event_ID, Name, Date, Description, Type_ID FROM Event where Date = '" + date + "'");
            while(resultSet.next())
            {
                orgEvents.add(new OrgEvent(resultSet.getInt("Event_ID"), resultSet.getString("Name"), resultSet.getString("Date"), resultSet.getString("Description"),
                        resultSet.getInt("Type_ID")));
            }
            return orgEvents;
        }
        catch (SQLException e)
        {
            return Collections.emptyList();
        }
    }
    public List<OrgEvent> findEventByDesc(String desc)
    {
        try(Statement statement = this.connection.createStatement())
        {
            List<OrgEvent> orgEvents = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT Event_ID, Name, Date, Description, Type_ID FROM Event where Description '" + desc + "'");
            while(resultSet.next())
            {
                orgEvents.add(new OrgEvent(resultSet.getInt("Event_ID"), resultSet.getString("Name"), resultSet.getString("Date"), resultSet.getString("Description"),
                        resultSet.getInt("Type_ID")));
            }
            return orgEvents;
        }
        catch(SQLException e)
        {
            return Collections.emptyList();
        }
    }
    public int findidbyType(String type)
    {
        try(Statement statement = this.connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery("SELECT Type_ID, Name, Description FROM Types");
            while(resultSet.next())
            {
                int id = resultSet.getInt("Type_ID");
                if(type.equals(resultSet.getString("Name"))) return id;
                resultSet.getString("Description");
            }
            return 9999;
        }
        catch(SQLException e)
        {
            return 9999;
        }
    }
    public String findTypeByID(int id)
    {
        String type = null;
        try(Statement statement = this.connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery("SELECT Type_ID, Name, Description FROM Types");
            while(resultSet.next())
            {
                if(id == resultSet.getInt("Type_ID")) return resultSet.getString("Name");
                resultSet.getString("Name");
                resultSet.getString("Description");
            }
            return type;
        }
        catch(SQLException e)
        {
            return type;
        }
    }
    public void addEvent(OrgEvent orgEvent) throws SQLException
    {
        try(PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Event(`Name`, `Date`, `Description`, `Type_ID`) VALUES(?, ?, ?, ?)"))
        {
            statement.setObject(1, orgEvent.name);
            statement.setObject(2, orgEvent.date);
            statement.setObject(3, orgEvent.description);
            statement.setObject(4, orgEvent.typeID);
            statement.execute();
        }
    }
    public void addType(EventType eventType) throws SQLException
    {
        try(PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Types(`Name`, `Description`) VALUES(?, ?)"))
        {
            statement.setObject(1, eventType.name);
            statement.setObject(2, eventType.Desc);
            statement.execute();
        }
    }
    public void deleteEvent(int id) throws SQLException
    {
        try(PreparedStatement statement = this.connection.prepareStatement("DELETE FROM Event WHERE Event_ID = ?"))
        {
            statement.setObject(1, id);
            statement.execute();
        }
    }
    public void deleteType(int id) throws SQLException
    {
        try(PreparedStatement statement = this.connection.prepareStatement("DELETE FROM Types WHERE Type_ID = ?"))
        {
            statement.setObject(1, id);
            statement.execute();
        }
    }
    public void updEvent(OrgEvent event, String type) throws SQLException
    {
        String oldName, oldDate, oldDesc;
        int oldTypeID;
        try(Statement statement = this.connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery("SELECT Event_ID, Name, Date, Description, Type_ID FROM Event where Event_ID = '" + event.ID + "'");
            oldName = resultSet.getString("Name");
            oldDate = resultSet.getString("Date");
            oldDesc = resultSet.getString("Description");
            oldTypeID = resultSet.getInt("Type_ID");
        }
        try(PreparedStatement pstmt = this.connection.prepareStatement("UPDATE Event SET Name = ?, Date = ?, Description = ?, Type_ID = ? WHERE Event_ID = ?"))
        {
            if(!event.name.equals("-")) pstmt.setString(1, event.name);
            else pstmt.setString(1, oldName);
            if(!event.date.equals("-")) pstmt.setString(2, event.date);
            else pstmt.setString(2, oldDate);
            if(!event.description.equals("-")) pstmt.setString(3, event.description);
            else pstmt.setString(3, oldDesc);
            if(!type.equals("-")) pstmt.setInt(4, event.typeID);
            else pstmt.setInt(4, oldTypeID);
            pstmt.setInt(5, event.ID);
            pstmt.executeUpdate();
        }
    }
    public void updType(EventType type) throws SQLException
    {
        String oldName, oldDescription;
        try(Statement statement = this.connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery("SELECT Name, Description FROM Types where Type_ID = '" + type.id + "'");
            oldName = resultSet.getString("Name");
            oldDescription = resultSet.getString("Description");
        }
        try(PreparedStatement pstmt = this.connection.prepareStatement("UPDATE Types SET Name = ?, Description = ? WHERE Type_ID = ?"))
        {
            if(!type.name.equals("-")) pstmt.setString(1, type.name);
            else pstmt.setString(1, oldName);
            if(!type.Desc.equals("-")) pstmt.setString(2, type.Desc);
            else pstmt.setString(2, oldDescription);
            pstmt.setInt(3, type.id);
            pstmt.executeUpdate();
        }
    }
    public boolean isEventEx(int id)
    {
        boolean isExist = false;
        List<OrgEvent> allOrgEvents = getAllEvents();
        for(OrgEvent orgEvent : allOrgEvents)
        {
            if(orgEvent.ID == id) isExist = true;
        }
        return isExist;
    }
    public boolean isTypeIDEx(int id)
    {
        boolean isExist = false;
        List<EventType> allEventTypes = getAllEventTypes();
        for(EventType eventType : allEventTypes)
        {
            if(eventType.id == id) isExist = true;
        }
        return isExist;
    }
}