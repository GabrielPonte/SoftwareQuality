<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="softwareQualityApp.informacoesStartForm.home.createOrEditLabel"
          data-cy="InformacoesStartFormCreateUpdateHeading"
          v-text="$t('softwareQualityApp.informacoesStartForm.home.createOrEditLabel')"
        >
          Create or edit a InformacoesStartForm
        </h2>
        <div v-if="informacoesProcess.processInstance">
          <akip-show-process-definition :processDefinition="informacoesProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="informacoesProcess.informacoes">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('softwareQualityApp.informacoesStartForm.opcao')"
                    for="informacoes-start-form-opcao"
                    >Opcao</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="opcao"
                    id="informacoes-start-form-opcao"
                    data-cy="opcao"
                    :class="{
                      valid: !$v.informacoesProcess.informacoes.opcao.$invalid,
                      invalid: $v.informacoesProcess.informacoes.opcao.$invalid,
                    }"
                    v-model="$v.informacoesProcess.informacoes.opcao.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('softwareQualityApp.informacoesStartForm.nomeCompleto')"
                    for="informacoes-start-form-nomeCompleto"
                    >Nome Completo</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="nomeCompleto"
                    id="informacoes-start-form-nomeCompleto"
                    data-cy="nomeCompleto"
                    :class="{
                      valid: !$v.informacoesProcess.informacoes.nomeCompleto.$invalid,
                      invalid: $v.informacoesProcess.informacoes.nomeCompleto.$invalid,
                    }"
                    v-model="$v.informacoesProcess.informacoes.nomeCompleto.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('softwareQualityApp.informacoesStartForm.email')"
                    for="informacoes-start-form-email"
                    >Email</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="email"
                    id="informacoes-start-form-email"
                    data-cy="email"
                    :class="{
                      valid: !$v.informacoesProcess.informacoes.email.$invalid,
                      invalid: $v.informacoesProcess.informacoes.email.$invalid,
                    }"
                    v-model="$v.informacoesProcess.informacoes.email.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('softwareQualityApp.informacoesStartForm.idade')"
                    for="informacoes-start-form-idade"
                    >Idade</label
                  >
                  <input
                    type="number"
                    class="form-control"
                    name="idade"
                    id="informacoes-start-form-idade"
                    data-cy="idade"
                    :class="{
                      valid: !$v.informacoesProcess.informacoes.idade.$invalid,
                      invalid: $v.informacoesProcess.informacoes.idade.$invalid,
                    }"
                    v-model.number="$v.informacoesProcess.informacoes.idade.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('softwareQualityApp.informacoesStartForm.opcaoEscolhida')"
                    for="informacoes-start-form-opcaoEscolhida"
                    >Opcao Escolhida</label
                  >
                  <select
                    class="form-control"
                    id="informacoes-start-form-opcaoEscolhida"
                    data-cy="opcaoEscolhida"
                    name="opcaoEscolhida"
                    v-model="informacoesProcess.informacoes.opcaoEscolhida"
                  >
                    <option v-bind:value="null"></option>
                    <option
                      v-bind:value="
                        informacoesProcess.informacoes.opcaoEscolhida &&
                        opcaoEscolhidaOption.id === informacoesProcess.informacoes.opcaoEscolhida.id
                          ? informacoesProcess.informacoes.opcaoEscolhida
                          : opcaoEscolhidaOption
                      "
                      v-for="opcaoEscolhidaOption in opcaoEscolhidas"
                      :key="opcaoEscolhidaOption.id"
                    >
                      {{ opcaoEscolhidaOption.opcaoInicial }}
                    </option>
                  </select>
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
            :disabled="$v.informacoesProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./informacoes-start-form-init.component.ts"></script>
