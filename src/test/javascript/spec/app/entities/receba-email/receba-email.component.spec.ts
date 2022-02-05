/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import RecebaEmailComponent from '@/entities/receba-email/receba-email.vue';
import RecebaEmailClass from '@/entities/receba-email/receba-email.component';
import RecebaEmailService from '@/entities/receba-email/receba-email.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('RecebaEmail Management Component', () => {
    let wrapper: Wrapper<RecebaEmailClass>;
    let comp: RecebaEmailClass;
    let recebaEmailServiceStub: SinonStubbedInstance<RecebaEmailService>;

    beforeEach(() => {
      recebaEmailServiceStub = sinon.createStubInstance<RecebaEmailService>(RecebaEmailService);
      recebaEmailServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<RecebaEmailClass>(RecebaEmailComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          recebaEmailService: () => recebaEmailServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      recebaEmailServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllRecebaEmails();
      await comp.$nextTick();

      // THEN
      expect(recebaEmailServiceStub.retrieve.called).toBeTruthy();
      expect(comp.recebaEmails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      recebaEmailServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeRecebaEmail();
      await comp.$nextTick();

      // THEN
      expect(recebaEmailServiceStub.delete.called).toBeTruthy();
      expect(recebaEmailServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
