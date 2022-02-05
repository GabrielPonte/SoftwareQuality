<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="softwareQualityApp.opcaoEscolhida.home.createOrEditLabel"
          data-cy="OpcaoEscolhidaCreateUpdateHeading"
          v-text="$t('softwareQualityApp.opcaoEscolhida.home.createOrEditLabel')"
        >
          Create or edit a OpcaoEscolhida
        </h2>
        <div>
          <div class="form-group" v-if="opcaoEscolhida.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="opcaoEscolhida.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('softwareQualityApp.opcaoEscolhida.opcaoInicial')"
              for="opcao-escolhida-opcaoInicial"
              >Opcao Inicial</label
            >
            <input
              type="text"
              class="form-control"
              name="opcaoInicial"
              id="opcao-escolhida-opcaoInicial"
              data-cy="opcaoInicial"
              :class="{ valid: !$v.opcaoEscolhida.opcaoInicial.$invalid, invalid: $v.opcaoEscolhida.opcaoInicial.$invalid }"
              v-model="$v.opcaoEscolhida.opcaoInicial.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.opcaoEscolhida.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./opcao-escolhida-update.component.ts"></script>
