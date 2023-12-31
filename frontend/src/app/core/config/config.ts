const rotaInterno = 'interno';

export const Config = {
    urlBackendPublico: 'http://localhost:8080/',

    rotaInterno: rotaInterno,

    // Rotas Users
    rotaUserObtemTodos: 'user/getAll',
    rotaUserObtem: 'user/getOne',
    rotaUserCria: 'user/create',
    rotaUserEdita: 'user/edit',
    rotaUserDeleta: 'user/delete',
    rotaUserLogin: 'user/login',

    // Rotas Usages
    rotaUsagesObtemTodos: 'usages/getAll',
    rotaUsagesObtem: 'usages/getOne',
    rotaUsagesCria: 'usages/create',
    rotaUsagesEdita: 'usages/edit',
    rotaUsagesDeleta: 'usages/delete',

    // Rotas Veiculo
    rotaVeiculoObtemTodos: 'veiculo/getAll',
    rotaVeiculoObtem: 'veiculo/getOne',
    rotaVeiculoCria: 'veiculo/create',
    rotaVeiculoCriaAll: 'veiculo/createAll',
    rotaVeiculoEdita: 'veiculo/edit',
    rotaVeiculoDeleta: 'veiculo/delete',
    
    // Rotas TagsTemporary
    rotaTagsTemporaryObtemTodos: 'tagsTemporary/getAll',
    rotaTagsTemporaryObtem: 'tagsTemporary/getOne',
    rotaTagsTemporaryCria: 'tagsTemporary/create',
    rotaTagsTemporaryEdita: 'tagsTemporary/edit',
    rotaTagsTemporaryDeleta: 'tagsTemporary/delete',

    // Rotas ParametroConfiguracao
    rotaParametroConfiguracaoObtemTodos: 'parametroConfiguracao/getAll',
    rotaParametroConfiguracaoObtem: 'parametroConfiguracao/getOne',
    rotaParametroConfiguracaoCria: 'parametroConfiguracao/create',
    rotaParametroConfiguracaoEdita: 'parametroConfiguracao/edit',
    rotaParametroConfiguracaoDeleta: 'parametroConfiguracao/delete',

    parametroId: 'id',
    nomeParametro: 'nomeParametro'
}