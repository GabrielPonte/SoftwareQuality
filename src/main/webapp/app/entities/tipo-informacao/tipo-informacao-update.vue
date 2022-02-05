<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="softwareQualityApp.tipoInformacao.home.createOrEditLabel"
          data-cy="TipoInformacaoCreateUpdateHeading"
          v-text="$t('softwareQualityApp.tipoInformacao.home.createOrEditLabel')"
        >
          Create or edit a TipoInformacao
        </h2>
        <div>
          <div class="form-group" v-if="tipoInformacao.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tipoInformacao.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('softwareQualityApp.tipoInformacao.tipoInfo')" for="tipo-informacao-tipoInfo"
              >Tipo Info</label
            >
            <input
              type="text"
              class="form-control"
              name="tipoInfo"
              id="tipo-informacao-tipoInfo"
              data-cy="tipoInfo"
              :class="{ valid: !$v.tipoInformacao.tipoInfo.$invalid, invalid: $v.tipoInformacao.tipoInfo.$invalid }"
              v-model="$v.tipoInformacao.tipoInfo.$model"
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
            :disabled="$v.tipoInformacao.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./tipo-informacao-update.component.ts"></script>
