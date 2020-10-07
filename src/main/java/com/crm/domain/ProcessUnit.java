package com.crm.domain;

import javax.persistence.*;
import java.util.Date;

/* Entity to handle the process unit of a ticket
has 1 to 1 relation with Tickets
has 1 to 1 relation with User (Empolyee)
has 1 to many relation with CommentsTicket
*/
@Entity
@Table(name = "ProcessingUnit")
public class ProcessUnit {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String state;           /*can be enum searh for enymtype.string - state(failed, completed, resolving, need attention)*/
private String admin;
private Date assigned;
private Date finished;
private Boolean isCompleted;
private Boolean isResolving;
@OneToOne
@JoinColumn(name = "tickets_id")
private Tickets tickets;
@ManyToOne
@JoinColumn(name = "employee_id")
private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Date getAssigned() {
        return assigned;
    }

    public void setAssigned(Date assigned) {
        this.assigned = assigned;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public Tickets getTickets() {
        return tickets;
    }

    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Boolean getResolving() {
        return isResolving;
    }

    public void setResolving(Boolean resolving) {
        isResolving = resolving;
    }

}
