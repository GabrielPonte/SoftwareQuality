<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="jhipsterApp.agendamentoStartForm.home.createOrEditLabel"
          data-cy="AgendamentoStartFormCreateUpdateHeading"
          v-text="$t('jhipsterApp.agendamentoStartForm.home.createOrEditLabel')"
        >
          Create or edit a AgendamentoStartForm
        </h2>
        <div v-if="agendamentoProcess.processInstance">
          <akip-show-process-definition :processDefinition="agendamentoProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="agendamentoProcess.agendamento">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('jhipsterApp.agendamentoStartForm.nomeCompleto')"
                    for="agendamento-start-form-nomeCompleto"
                    >Nome Completo</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="nomeCompleto"
                    id="agendamento-start-form-nomeCompleto"
                    data-cy="nomeCompleto"
                    :class="{
                      valid: !$v.agendamentoProcess.agendamento.nomeCompleto.$invalid,
                      invalid: $v.agendamentoProcess.agendamento.nomeCompleto.$invalid,
                    }"
                    v-model="$v.agendamentoProcess.agendamento.nomeCompleto.$model"
                  />
                </div>
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.agendamentoStartForm.idade')" for="agendamento-start-form-idade"
                    >Idade</label
                  >
                  <input
                    type="number"
                    class="form-control"
                    name="idade"
                    id="agendamento-start-form-idade"
                    data-cy="idade"
                    :class="{
                      valid: !$v.agendamentoProcess.agendamento.idade.$invalid,
                      invalid: $v.agendamentoProcess.agendamento.idade.$invalid,
                    }"
                    v-model.number="$v.agendamentoProcess.agendamento.idade.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('jhipsterApp.agendamentoStartForm.qtdVacinas')"
                    for="agendamento-start-form-qtdVacinas"
                    >Qtd Vacinas</label
                  >
                  <input
                    type="number"
                    class="form-control"
                    name="qtdVacinas"
                    id="agendamento-start-form-qtdVacinas"
                    data-cy="qtdVacinas"
                    :class="{
                      valid: !$v.agendamentoProcess.agendamento.qtdVacinas.$invalid,
                      invalid: $v.agendamentoProcess.agendamento.qtdVacinas.$invalid,
                    }"
                    v-model.number="$v.agendamentoProcess.agendamento.qtdVacinas.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('jhipsterApp.agendamentoStartForm.sintomas')"
                    for="agendamento-start-form-sintomas"
                    >Sintomas</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="sintomas"
                    id="agendamento-start-form-sintomas"
                    data-cy="sintomas"
                    :class="{
                      valid: !$v.agendamentoProcess.agendamento.sintomas.$invalid,
                      invalid: $v.agendamentoProcess.agendamento.sintomas.$invalid,
                    }"
                    v-model="$v.agendamentoProcess.agendamento.sintomas.$model"
                  />
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.agendamentoProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./agendamento-start-form-init.component.ts"></script>
