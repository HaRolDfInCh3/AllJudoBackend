# ExempleMongoDB

// 1 nor
{$nor:[{"Champion_id" : NumberLong(0)}]}

// 2 convertion long vers int 
db.videos.update({}, [ {$set:{ "Champion_id" : {$toInt: "$Champion_id"}}} ], {multi:true})

// 3 ajout d'un champ
db.banniere160x600.updateMany({},{$set:{"taille" : "160x600"}})
db.banniere300x250.updateMany({},{$set:{"taille" : "300x250"}})
db.banniere300x60.updateMany({},{$set:{"taille" : "300x60"}})
db.banniere336x280.updateMany({},{$set:{"taille" : "336x280"}})
db.banniere728x90.updateMany({},{$set:{"taille" : "728x90"}})
db.banniere768x90.updateMany({},{$set:{"taille" : "768x90"}})

// 4 expressions regulieres
{"NomDepartement" : { $regex: "Côte*" }}

// 5 ajout d'une date
db.champions.updateMany({},{$set:{"DateNaissance2" : ISODate("2022-04-23T22:00:00Z")}})
db.champions.updateMany({},{$set:{"DateChangementNat2" : ISODate("2022-04-23T22:00:00Z")}})

// 6 supression d'un champ
db.champions.updateMany({},{$unset:{"DateNaissance" : ""}})
db.champions.updateMany({},{$unset:{"DateChangementNat" : ""}})

// 7 modification d'un champ
db.champions.updateMany( {}, { $rename: { "DateNaissance2": "DateNaissance" } } )
db.champions.updateMany( {}, { $rename: { "DateChangementNat2": "DateChangementNat" } } )

db.champion_popularite1.updateMany({},{$set:{"date2" : ISODate("2022-04-23T22:00:00Z")}})

db.champion_popularite1.updateMany({},{$unset:{"date" : ""}})
db.champion_popularite1.updateMany({"date" : "0000-00-00 00:00:00"},{$set:{"date" : "2022-04-20 14:25:00"}})
db.champion_popularite.find({"date" : "0000-00-00 00:00:00"},{})

{$or:[{"CategorieAge":{$gte:0}}]}

// regex caracteres speciaux
{"Grade" : {$regex:"\\?\\?", '$options' : 'i'}}
db.clubs.updateMany({"Valide" :1},{$set:{"Valide" : true}})