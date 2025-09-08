export const environmentDev = {
  baseUrl: "/host",
  endPoint: {
    personne: {
      getAll: "personnes/toutes",
      getOne: "personnes/recuperer",
      create: "personnes/enregistrer",
      delete: "personnes/supprimer",
      update: "personnes/modifier"
    },
    departement : {
      getAll: "departements/toutes",
    }
  }
}
