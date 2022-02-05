<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="softwareQualityApp.recebaEmail.home.createOrEditLabel"
          data-cy="RecebaEmailCreateUpdateHeading"
          v-text="$t('softwareQualityApp.recebaEmail.home.createOrEditLabel')"
        >
          Create or edit a RecebaEmail
        </h2>
        <div>
          <div class="form-group" v-if="recebaEmail.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="recebaEmail.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('softwareQualityApp.recebaEmail.opcaoEmail')" for="receba-email-opcaoEmail"
              >Opcao Email</label
            >
            <input
              type="text"
              class="form-control"
              name="opcaoEmail"
              id="receba-email-opcaoEmail"
              data-cy="opcaoEmail"
              :class="{ valid: !$v.recebaEmail.opcaoEmail.$invalid, invalid: $v.recebaEmail.opcaoEmail.$invalid }"
              v-model="$v.recebaEmail.opcaoEmail.$model"
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
            :disabled="$v.recebaEmail.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./receba-email-update.component.ts"></script>
