package hellojpa;

import javax.persistence.*;

@Entity
public class MemberProduct {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member2 member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product2 product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member2 getMember() {
        return member;
    }

    public void setMember(Member2 member) {
        this.member = member;
    }

    public Product2 getProduct() {
        return product;
    }

    public void setProduct(Product2 product) {
        this.product = product;
    }
}
