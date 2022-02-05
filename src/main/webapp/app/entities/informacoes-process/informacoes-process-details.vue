<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="informacoesProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="informacoesProcessDetailsHeading">
          <span v-text="$t('softwareQualityApp.informacoesProcess.detail.title')">InformacoesProcess</span> {{ informacoesProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="informacoesProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('softwareQualityApp.informacoesProcess.detail.title')">InformacoesProcess</span> #{{ informacoesProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="informacoesProcess.informacoes">
              <h5 class="card-header">
                <span class="title"> Informacoes #{{ informacoesProcess.informacoes.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.opcao')">opcao</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="opcao"
                    id="informacoes-opcao"
                    data-cy="opcao"
                    v-model="informacoesProcess.informacoes.opcao"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.nomeCompleto')">nomeCompleto</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="nomeCompleto"
                    id="informacoes-nomeCompleto"
                    data-cy="nomeCompleto"
                    v-model="informacoesProcess.informacoes.nomeCompleto"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.cpf')">cpf</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="cpf"
                    id="informacoes-cpf"
                    data-cy="cpf"
                    v-model="informacoesProcess.informacoes.cpf"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.email')">email</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="email"
                    id="informacoes-email"
                    data-cy="email"
                    v-model="informacoesProcess.informacoes.email"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.idade')">idade</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="idade"
                    id="informacoes-idade"
                    data-cy="idade"
                    v-model="informacoesProcess.informacoes.idade"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.sintomas')">sintomas</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="sintomas"
                    id="informacoes-sintomas"
                    data-cy="sintomas"
                    v-model="informacoesProcess.informacoes.sintomas"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.qtdVacinas')">qtdVacinas</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="qtdVacinas"
                    id="informacoes-qtdVacinas"
                    data-cy="qtdVacinas"
                    v-model="informacoesProcess.informacoes.qtdVacinas"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.cidade')">cidade</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="cidade"
                    id="informacoes-cidade"
                    data-cy="cidade"
                    v-model="informacoesProcess.informacoes.cidade"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.estado')">estado</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="estado"
                    id="informacoes-estado"
                    data-cy="estado"
                    v-model="informacoesProcess.informacoes.estado"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.endereco')">endereco</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="endereco"
                    id="informacoes-endereco"
                    data-cy="endereco"
                    v-model="informacoesProcess.informacoes.endereco"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.complemento')">complemento</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="complemento"
                    id="informacoes-complemento"
                    data-cy="complemento"
                    v-model="informacoesProcess.informacoes.complemento"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.cep')">cep</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="cep"
                    id="informacoes-cep"
                    data-cy="cep"
                    v-model="informacoesProcess.informacoes.cep"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.nomeDoPosto')">nomeDoPosto</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="nomeDoPosto"
                    id="informacoes-nomeDoPosto"
                    data-cy="nomeDoPosto"
                    v-model="informacoesProcess.informacoes.nomeDoPosto"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.estadoDoPosto')">estadoDoPosto</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="estadoDoPosto"
                    id="informacoes-estadoDoPosto"
                    data-cy="estadoDoPosto"
                    v-model="informacoesProcess.informacoes.estadoDoPosto"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.cidadeDoPosto')">cidadeDoPosto</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="cidadeDoPosto"
                    id="informacoes-cidadeDoPosto"
                    data-cy="cidadeDoPosto"
                    v-model="informacoesProcess.informacoes.cidadeDoPosto"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.data')">data</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="data"
                    id="informacoes-data"
                    data-cy="data"
                    v-model="informacoesProcess.informacoes.data"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.hora')">hora</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="hora"
                    id="informacoes-hora"
                    data-cy="hora"
                    v-model="informacoesProcess.informacoes.hora"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.tipoDeInformacao')"
                    >tipoDeInformacao</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="tipoDeInformacao"
                    id="informacoes-tipoDeInformacao"
                    data-cy="tipoDeInformacao"
                    v-model="informacoesProcess.informacoes.tipoDeInformacao"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.local')">local</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="local"
                    id="informacoes-local"
                    data-cy="local"
                    v-model="informacoesProcess.informacoes.local"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.receberEmail')">receberEmail</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="receberEmail"
                    id="informacoes-receberEmail"
                    data-cy="receberEmail"
                    v-model="informacoesProcess.informacoes.receberEmail"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('softwareQualityApp.informacoesProcess.localDesejado')"
                    for="informacoes-process-localDesejado"
                    >Local Desejado</label
                  >
                  <input
                    v-if="informacoesProcess.informacoes.localDesejado"
                    readonly
                    type="text"
                    class="form-control"
                    name="localDesejado"
                    id="informacoes-localDesejado"
                    data-cy="localDesejado"
                    :value="informacoesProcess.informacoes.localDesejado.localCovid"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="localDesejado"
                    id="informacoes-localDesejado"
                    data-cy="localDesejado"
                    value=""
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('softwareQualityApp.informacoesProcess.opcaoEscolhida')"
                    for="informacoes-process-opcaoEscolhida"
                    >Opcao Escolhida</label
                  >
                  <input
                    v-if="informacoesProcess.informacoes.opcaoEscolhida"
                    readonly
                    type="text"
                    class="form-control"
                    name="opcaoEscolhida"
                    id="informacoes-opcaoEscolhida"
                    data-cy="opcaoEscolhida"
                    :value="informacoesProcess.informacoes.opcaoEscolhida.opcaoInicial"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="opcaoEscolhida"
                    id="informacoes-opcaoEscolhida"
                    data-cy="opcaoEscolhida"
                    value=""
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('softwareQualityApp.informacoesProcess.postoSaude')"
                    for="informacoes-process-postoSaude"
                    >Posto Saude</label
                  >
                  <input
                    v-if="informacoesProcess.informacoes.postoSaude"
                    readonly
                    type="text"
                    class="form-control"
                    name="postoSaude"
                    id="informacoes-postoSaude"
                    data-cy="postoSaude"
                    :value="informacoesProcess.informacoes.postoSaude.nomePosto"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="postoSaude"
                    id="informacoes-postoSaude"
                    data-cy="postoSaude"
                    value=""
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('softwareQualityApp.informacoesProcess.recebaEmail')"
                    for="informacoes-process-recebaEmail"
                    >Receba Email</label
                  >
                  <input
                    v-if="informacoesProcess.informacoes.recebaEmail"
                    readonly
                    type="text"
                    class="form-control"
                    name="recebaEmail"
                    id="informacoes-recebaEmail"
                    data-cy="recebaEmail"
                    :value="informacoesProcess.informacoes.recebaEmail.opcaoEmail"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="recebaEmail"
                    id="informacoes-recebaEmail"
                    data-cy="recebaEmail"
                    value=""
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('softwareQualityApp.informacoesProcess.tipoInformacao')"
                    for="informacoes-process-tipoInformacao"
                    >Tipo Informacao</label
                  >
                  <input
                    v-if="informacoesProcess.informacoes.tipoInformacao"
                    readonly
                    type="text"
                    class="form-control"
                    name="tipoInformacao"
                    id="informacoes-tipoInformacao"
                    data-cy="tipoInformacao"
                    :value="informacoesProcess.informacoes.tipoInformacao.tipoInfo"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="tipoInformacao"
                    id="informacoes-tipoInformacao"
                    data-cy="tipoInformacao"
                    value=""
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./informacoes-process-details.component.ts"></script>
