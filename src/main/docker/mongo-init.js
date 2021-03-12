db.auth('root','root')
db = db.getSiblingDB('voisin_online')



db.createUser({user: "voisin", pwd: "voisin", roles:[{ role: "readwrite", db: "voisin_online"}], mechanisms:[ "SCRAM-SHA-1"] })
db.createUser({ user: "root" , pwd: "root", roles: ["userAdminAnyDatabase", "dbAdminAnyDatabase", "readWriteAnyDatabase"]})
