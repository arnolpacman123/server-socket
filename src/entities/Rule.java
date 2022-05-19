package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "rule", catalog = "distribuidos", schema = "")
@NamedQueries({
    @NamedQuery(name = "Rule.findAll", query = "SELECT r FROM Rule r"),
    @NamedQuery(name = "Rule.findById", query = "SELECT r FROM Rule r WHERE r.id = :id"),
    @NamedQuery(name = "Rule.findByRi", query = "SELECT r FROM Rule r WHERE r.ri = :ri"),
    @NamedQuery(name = "Rule.findByRf", query = "SELECT r FROM Rule r WHERE r.rf = :rf"),
    @NamedQuery(name = "Rule.findByMessage", query = "SELECT r FROM Rule r WHERE r.message = :message"),
    @NamedQuery(name = "Rule.findByMail", query = "SELECT r FROM Rule r WHERE r.mail = :mail")})
public class Rule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ri", nullable = false)
    private int ri;
    @Basic(optional = false)
    @Column(name = "rf", nullable = false)
    private int rf;
    @Column(name = "message", length = 500)
    private String message;
    @Basic(optional = false)
    @Column(name = "mail", nullable = false, length = 100)
    private String mail;

    public Rule() {
    }

    public Rule(Integer id) {
        this.id = id;
    }

    public Rule(Integer id, int ri, int rf, String mail) {
        this.id = id;
        this.ri = ri;
        this.rf = rf;
        this.mail = mail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRi() {
        return ri;
    }

    public void setRi(int ri) {
        this.ri = ri;
    }

    public int getRf() {
        return rf;
    }

    public void setRf(int rf) {
        this.rf = rf;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rule)) {
            return false;
        }
        Rule other = (Rule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rule[ id=" + id + " ]";
    }
    
}
