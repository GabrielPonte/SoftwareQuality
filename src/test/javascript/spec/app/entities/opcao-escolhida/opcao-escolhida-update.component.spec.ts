/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import OpcaoEscolhidaUpdateComponent from '@/entities/opcao-escolhida/opcao-escolhida-update.vue';
import OpcaoEscolhidaClass from '@/entities/opcao-escolhida/opcao-escolhida-update.component';
import OpcaoEscolhidaService from '@/entities/opcao-escolhida/opcao-escolhida.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('OpcaoEscolhida Management Update Component', () => {
    let wrapper: Wrapper<OpcaoEscolhidaClass>;
    let comp: OpcaoEscolhidaClass;
    let opcaoEscolhidaServiceStub: SinonStubbedInstance<OpcaoEscolhidaService>;

    beforeEach(() => {
      opcaoEscolhidaServiceStub = sinon.createStubInstance<OpcaoEscolhidaService>(OpcaoEscolhidaService);

      wrapper = shallowMount<OpcaoEscolhidaClass>(OpcaoEscolhidaUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          opcaoEscolhidaService: () => opcaoEscolhidaServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.opcaoEscolhida = entity;
        opcaoEscolhidaServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(opcaoEscolhidaServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.opcaoEscolhida = entity;
        opcaoEscolhidaServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(opcaoEscolhidaServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundOpcaoEscolhida = { id: 123 };
        opcaoEscolhidaServiceStub.find.resolves(foundOpcaoEscolhida);
        opcaoEscolhidaServiceStub.retrieve.resolves([foundOpcaoEscolhida]);

        // WHEN
        comp.beforeRouteEnter({ params: { opcaoEscolhidaId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.opcaoEscolhida).toBe(foundOpcaoEscolhida);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
