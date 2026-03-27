package com.ecommerce.app.data.model.address;

import android.os.Parcelable;
import kotlinx.parcelize.Parcelize;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\"\u001a\u00020\bH\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010$\u001a\u00020\u0005H\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\rH\u00c6\u0003J\t\u0010\'\u001a\u00020\u000fH\u00c6\u0003Je\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u00c6\u0001J\t\u0010)\u001a\u00020\bH\u00d6\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u00d6\u0003J\t\u0010.\u001a\u00020\bH\u00d6\u0001J\t\u0010/\u001a\u00020\u0005H\u00d6\u0001J\u0019\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\bH\u00d6\u0001R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014\u00a8\u00065"}, d2 = {"Lcom/ecommerce/app/data/model/address/AddressResponse;", "Landroid/os/Parcelable;", "id", "", "name", "", "street", "number", "", "complement", "neighborhood", "postalCode", "city", "Lcom/ecommerce/app/data/model/address/CityResponse;", "state", "Lcom/ecommerce/app/data/model/address/StateResponse;", "(JLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/ecommerce/app/data/model/address/CityResponse;Lcom/ecommerce/app/data/model/address/StateResponse;)V", "getCity", "()Lcom/ecommerce/app/data/model/address/CityResponse;", "getComplement", "()Ljava/lang/String;", "getId", "()J", "getName", "getNeighborhood", "getNumber", "()I", "getPostalCode", "getState", "()Lcom/ecommerce/app/data/model/address/StateResponse;", "getStreet", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_debug"})
@kotlinx.parcelize.Parcelize()
public final class AddressResponse implements android.os.Parcelable {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String street = null;
    private final int number = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String complement = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String neighborhood = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String postalCode = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.model.address.CityResponse city = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.model.address.StateResponse state = null;
    
    public AddressResponse(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String street, int number, @org.jetbrains.annotations.Nullable()
    java.lang.String complement, @org.jetbrains.annotations.NotNull()
    java.lang.String neighborhood, @org.jetbrains.annotations.NotNull()
    java.lang.String postalCode, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.address.CityResponse city, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.address.StateResponse state) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStreet() {
        return null;
    }
    
    public final int getNumber() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getComplement() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNeighborhood() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPostalCode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.model.address.CityResponse getCity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.model.address.StateResponse getState() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.model.address.CityResponse component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.model.address.StateResponse component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ecommerce.app.data.model.address.AddressResponse copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String street, int number, @org.jetbrains.annotations.Nullable()
    java.lang.String complement, @org.jetbrains.annotations.NotNull()
    java.lang.String neighborhood, @org.jetbrains.annotations.NotNull()
    java.lang.String postalCode, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.address.CityResponse city, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.address.StateResponse state) {
        return null;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
}