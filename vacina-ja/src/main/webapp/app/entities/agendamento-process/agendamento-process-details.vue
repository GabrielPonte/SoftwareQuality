<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="agendamentoProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="agendamentoProcessDetailsHeading">
          <span v-text="$t('jhipsterApp.agendamentoProcess.detail.title')">AgendamentoProcess</span> {{ agendamentoProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="agendamentoProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('jhipsterApp.agendamentoProcess.detail.title')">AgendamentoProcess</span> #{{ agendamentoProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="agendamentoProcess.agendamento">
              <h5 class="card-header">
                <span class="title"> Agendamento #{{ agendamentoProcess.agendamento.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.nomeCompleto')">nomeCompleto</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="nomeCompleto"
                    id="agendamento-nomeCompleto"
                    data-cy="nomeCompleto"
                    v-model="agendamentoProcess.agendamento.nomeCompleto"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.CPF')">CPF</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="CPF"
                    id="agendamento-CPF"
                    data-cy="CPF"
                    v-model="agendamentoProcess.agendamento.CPF"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.idade')">idade</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="idade"
                    id="agendamento-idade"
                    data-cy="idade"
                    v-model="agendamentoProcess.agendamento.idade"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.sintomas')">sintomas</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="sintomas"
                    id="agendamento-sintomas"
                    data-cy="sintomas"
                    v-model="agendamentoProcess.agendamento.sintomas"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.qtdVacinas')">qtdVacinas</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="qtdVacinas"
                    id="agendamento-qtdVacinas"
                    data-cy="qtdVacinas"
                    v-model="agendamentoProcess.agendamento.qtdVacinas"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.cidade')">cidade</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="cidade"
                    id="agendamento-cidade"
                    data-cy="cidade"
                    v-model="agendamentoProcess.agendamento.cidade"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.estado')">estado</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="estado"
                    id="agendamento-estado"
                    data-cy="estado"
                    v-model="agendamentoProcess.agendamento.estado"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.endereco')">endereco</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="endereco"
                    id="agendamento-endereco"
                    data-cy="endereco"
                    v-model="agendamentoProcess.agendamento.endereco"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.complemento')">complemento</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="complemento"
                    id="agendamento-complemento"
                    data-cy="complemento"
                    v-model="agendamentoProcess.agendamento.complemento"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.CEP')">CEP</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="CEP"
                    id="agendamento-CEP"
                    data-cy="CEP"
                    v-model="agendamentoProcess.agendamento.CEP"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.nomePosto')">nomePosto</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="nomePosto"
                    id="agendamento-nomePosto"
                    data-cy="nomePosto"
                    v-model="agendamentoProcess.agendamento.nomePosto"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.data')">data</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="data"
                    id="agendamento-data"
                    data-cy="data"
                    v-model="agendamentoProcess.agendamento.data"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoProcess.hora')">hora</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="hora"
                    id="agendamento-hora"
                    data-cy="hora"
                    v-model="agendamentoProcess.agendamento.hora"
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

<script lang="ts" src="./agendamento-process-details.component.ts"></script>
