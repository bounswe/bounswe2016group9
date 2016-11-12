package boun.cmpe451.group9.Models.DB;

import boun.cmpe451.group9.Models.Base;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Created by seha on 12.11.2016.
 */

@Entity
@Table(name="Relation_Comment")
public class RelationComment extends Base{

    @Column(name = "VOTE_COUNT")
    private int voteCount;
}
