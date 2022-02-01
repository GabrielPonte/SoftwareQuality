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
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.name')">name</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="name"
                    id="informacoes-name"
                    data-cy="name"
                    v-model="informacoesProcess.informacoes.name"
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
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.CPF')">CPF</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="CPF"
                    id="informacoes-CPF"
                    data-cy="CPF"
                    v-model="informacoesProcess.informacoes.CPF"
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
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.CEP')">CEP</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="CEP"
                    id="informacoes-CEP"
                    data-cy="CEP"
                    v-model="informacoesProcess.informacoes.CEP"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('softwareQualityApp.informacoesProcess.nomePosto')">nomePosto</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="nomePosto"
                    id="informacoes-nomePosto"
                    data-cy="nomePosto"
                    v-model="informacoesProcess.informacoes.nomePosto"
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
