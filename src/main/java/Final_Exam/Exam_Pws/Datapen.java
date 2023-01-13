/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Exam.Exam_Pws;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AXEL
 */
@Entity
@Table(name = "datapen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datapen.findAll", query = "SELECT d FROM Datapen d"),
    @NamedQuery(name = "Datapen.findById", query = "SELECT d FROM Datapen d WHERE d.id = :id"),
    @NamedQuery(name = "Datapen.findByNik", query = "SELECT d FROM Datapen d WHERE d.nik = :nik"),
    @NamedQuery(name = "Datapen.findByNama", query = "SELECT d FROM Datapen d WHERE d.nama = :nama"),
    @NamedQuery(name = "Datapen.findByTanggal", query = "SELECT d FROM Datapen d WHERE d.tanggal = :tanggal"),
    @NamedQuery(name = "Datapen.findByTime", query = "SELECT d FROM Datapen d WHERE d.time = :time")})
public class Datapen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;
    @Column(name = "NIK")
    private String nik;
    @Column(name = "Nama")
    private String nama;
    @Column(name = "Tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @Lob
    @Column(name = "Photo")
    private byte[] photo;
    @Basic(optional = false)
    @Column(name = "Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Datapen() {
    }

    public Datapen(String id) {
        this.id = id;
    }

    public Datapen(String id, Date time) {
        this.id = id;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
        if (!(object instanceof Datapen)) {
            return false;
        }
        Datapen other = (Datapen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Final_Exam.Exam_Pws.Datapen[ id=" + id + " ]";
    }
    
}
