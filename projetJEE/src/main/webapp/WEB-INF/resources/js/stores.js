function removeStore(storeId, storeName) {
    if(confirm("Do you want to delete the store '"+storeName+"'?"))
        document.location.href="${cp}/DeleteStore?storeId="+storeId;
}
