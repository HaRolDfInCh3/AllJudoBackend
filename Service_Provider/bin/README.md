# ExempleMongoDB
db.banniere160x600.updateMany({},{$set:{"taille" : "160x600"}})
db.banniere300x250.updateMany({},{$set:{"taille" : "300x250"}})
db.banniere300x60.updateMany({},{$set:{"taille" : "300x60"}})
db.banniere336x280.updateMany({},{$set:{"taille" : "336x280"}})
db.banniere728x90.updateMany({},{$set:{"taille" : "728x90"}})
db.banniere768x90.updateMany({},{$set:{"taille" : "768x90"}})
{"NomDepartement" : { $regex: "Côte*" }}
db.champions.updateMany({},{$set:{"DateNaissance2" : ISODate("2022-04-23T22:00:00Z")}})
db.champions.updateMany({},{$set:{"DateChangementNat2" : ISODate("2022-04-23T22:00:00Z")}})
db.champions.updateMany({},{$unset:{"DateNaissance" : ""}})
db.champions.updateMany({},{$unset:{"DateChangementNat" : ""}})
db.champions.updateMany( {}, { $rename: { "DateNaissance2": "DateNaissance" } } )
db.champions.updateMany( {}, { $rename: { "DateChangementNat2": "DateChangementNat" } } )

db.champion_popularite1.updateMany({},{$set:{"date2" : ISODate("2022-04-23T22:00:00Z")}})

db.champion_popularite1.updateMany({},{$unset:{"date" : ""}})
db.champion_popularite1.updateMany({"date" : "0000-00-00 00:00:00"},{$set:{"date" : "2022-04-20 14:25:00"}})
db.champion_popularite.find({"date" : "0000-00-00 00:00:00"},{})
